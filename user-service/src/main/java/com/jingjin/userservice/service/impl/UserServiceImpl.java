package com.jingjin.userservice.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingjin.common.exception.BusinessException;
import com.jingjin.common.result.ErrorCode;

import com.jingjin.common.utils.JavaMailUtils;
import com.jingjin.jwtutil.jwtUtil.JwtTokenUtil;
import com.jingjin.model.user.dto.user.UploadPasswordDTO;
import com.jingjin.model.user.dto.user.UserRegisterDTO;
import com.jingjin.model.user.po.User;
import com.jingjin.model.user.po.UserRole;
import com.jingjin.serviceClient.service.order.OrderFeignClient;
import com.jingjin.userservice.mapper.PermissionMapper;
import com.jingjin.userservice.mapper.RolePermissionMapper;
import com.jingjin.userservice.mapper.UserMapper;
import com.jingjin.userservice.mapper.UserRoleMapper;
import com.jingjin.userservice.service.UserService;
import com.jingjin.userservice.util.UploadUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.jingjin.common.exception.ThrowUtils.throwIf;
import static com.jingjin.common.result.ErrorCode.LOGOUT_ERROR;
import static com.jingjin.common.result.ErrorCode.SYSTEM_ERROR;

/**
 * 用户服务层
 *
 * @author fxab
 * @date 2024/07/19
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    /**
     * jwt令牌工具类
     */
    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource
    private UploadUtil uploadUtil;

    /**
     * redis工具类
     */
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 邮箱验证码服务工具类
     */
    @Resource
    private JavaMailUtils javaMailUtils;

    /**
     * 用户-角色映射器
     */
    @Resource
    private UserRoleMapper userRoleMapper;

    /**
     * 角色权限映射器
     */
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 权限映射器
     */
    @Resource
    private PermissionMapper permissionMapper;


    /**
     * 兔子模板
     */
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 假装订购客户
     */
    @Resource
    private OrderFeignClient orderFeignClient;

    /**
     * 用户映射器
     */
    @Resource
    private UserMapper userMapper;

    /**
     * 盐
     */
    @Value("${user.salt}")
    private String SALT;

    /**
     * 用户注册
     *
     * @param userRegisterDTO 用户注册dto
     * @return {@link Boolean}
     */
    @Override
    public Boolean userRegister(UserRegisterDTO userRegisterDTO) {
        String email = userRegisterDTO.getEmail();
        String password = userRegisterDTO.getPassword();

        //1.账号查重
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "邮箱重复");
        }
        //2.密码加密
        String digestPassword = DigestUtil.md5Hex(SALT + password);

        // 3.验证码验证
        if (email != null) {
            boolean isCodeValid = confirmEmail(email, userRegisterDTO.getEmailCode());
            if (!isCodeValid) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "验证码错误或已过期");
            }
        }
        //4.写入数据库：用户表和用户-角色关系表（默认都是角色都是网站用户）
        User user = User.builder().email(email).password(digestPassword).build();
        int insertUser = userMapper.insert(user);

        // 写进用户-角色表
        UserRole userRole = UserRole.builder().userId(user.getId()).build();
        int insertUserRole = userRoleMapper.insert(userRole);
        if (insertUser <= 0 || insertUserRole <= 0) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
        }

        return true;
    }

    @Override
    public Boolean uploadAvatar(MultipartFile avatar,String userId) throws IOException {
        String avatarUrl = uploadUtil.uploadImg(avatar);
        User user = User.builder().id(userId).avatarUrl(avatarUrl).build();
        Boolean isSuccess = updateById(user);
        return isSuccess;
    }

    @Override
    public byte[] getAvatar(String userId) throws IOException {
        String avatarUrl = getById(userId).getAvatarUrl();
        throwIf(StringUtils.isEmpty(avatarUrl),SYSTEM_ERROR);
        URL url = new URL(avatarUrl);
        InputStream is = url.openStream();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = is.read(buffer)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        is.close();
        byte[] imageBytes = os.toByteArray();


        return imageBytes;
    }

    /**
     * 邮箱验证码的发送
     * @param email 用户邮箱
     * @return Boolean
     */
    @Override
    public Boolean sendEmail(String email) {
        // 生成6位随机验证码
        String verificationCode = String.format("%06d", new Random().nextInt(1000000));

        // 将验证码存储到 Redis 中，设置2分钟的过期时间
        stringRedisTemplate.opsForValue().set("EMAIL_VERIFICATION_CODE:" + email, verificationCode, 2, TimeUnit.MINUTES);

        // 发送邮件
        try {
            javaMailUtils.sendMessage(email, verificationCode);
        } catch (Exception e) {
            throw new RuntimeException("发送邮件失败", e);
        }
        return true;
    }

    /**
     * 验证邮箱验证码
     * @param email 邮箱账号
     * @param emailCode 邮箱验证码
     * @return 正确与否
     */
    @Override
    public Boolean confirmEmail(String email, String emailCode) {
        // 从 Redis 中获取验证码
        String storedCode = stringRedisTemplate.opsForValue().get("EMAIL_VERIFICATION_CODE:" + email);

        // 验证码是否存在以及是否匹配
        return storedCode != null && storedCode.equals(emailCode);
    }

    /**
     * 用户密码重置
     * @param uploadPasswordDTO 密码重置DTO
     */
    @Override
    public Boolean passwordReWrite(UploadPasswordDTO uploadPasswordDTO) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        // 1.这里用email来标识用户
        String email = uploadPasswordDTO.getEmail();
        String newPassword = uploadPasswordDTO.getNewPassword();
        // 2.密码加密
        String digestNewPassword = DigestUtil.md5Hex(SALT + newPassword);
        updateWrapper.eq("email", email).set("password", digestNewPassword);
        int update = userMapper.update(null, updateWrapper);
        return update > 0;
    }


    /**
     * 用户登录
     *
     * @param account  帐户
     * @param password 密码
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    @Override
    public Map<String, Object> userLogin(String account, String password) {
        // 1.参数校验
        if (StringUtils.isAnyBlank(account, password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        // 2.使用MD5对密码进行加密
        String encryptPassword = DigestUtil.md5Hex(SALT + password);

        // 3.查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", account);
        queryWrapper.eq("password", encryptPassword);
        User user = this.baseMapper.selectOne(queryWrapper);
        // 用户不存在
        if (user == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在或密码错误");
        }

        // 4. 查询用户的Id和用户权限
        List<String> roleIds = userRoleMapper.findRoleIdsByUserId(user.getId());
        List<String> perms = new ArrayList<>();

        if (!roleIds.isEmpty()) {
            // 查询所有角色的权限
            List<String> permissionIds = rolePermissionMapper.findPermissionsByRoleIds(roleIds);

            // 去重处理
            permissionIds = permissionIds.stream().distinct().collect(Collectors.toList());

            // 加载权限路由
            perms = permissionMapper.findPermsByPermissionIds(permissionIds);

            // 去重处理
            perms = perms.stream().distinct().collect(Collectors.toList());

        } else {
            // 处理没有角色的情况，可能返回一个空列表或抛出异常
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "改用户无角色身份");
        }

        // 3. 记录用户的Id和用户权限
        Map<String, Object> tokenMap = jwtTokenUtil.
                generateTokenAndRefreshToken(String.valueOf(user.getId()), perms);

        return tokenMap;
    }

    /**
     * 刷新令牌
     *
     * @param refreshToken 刷新令牌
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    @Override
    public Map<String, Object> refreshToken(String refreshToken) {
        //未获取到token
        throwIf(StringUtils.isEmpty(refreshToken),ErrorCode.TOKEN_MISSION);
        //token无效或过期
        throwIf(jwtTokenUtil.isTokenExpired(refreshToken),ErrorCode.TOKEN_INVALID);
        String userId = jwtTokenUtil.getUserIdFromToken(refreshToken);
        String username = jwtTokenUtil.getUserNameFromToken(refreshToken);
        //判断令牌是否在redis中
        throwIf(jwtTokenUtil.isRefreshTokenNotExistCache(refreshToken),ErrorCode.TOKEN_INVALID);
        //刷新token，refresh仅使用一次，用完即删除
        Map<String, Object> tokenMap = jwtTokenUtil.refreshTokenAndGenerateToken(userId, username);
        return tokenMap;
    }

    /**
     * 注销
     *
     * @param userId 用户id
     * @return {@link Boolean}
     */
    @Override
    public Boolean logout(String userId) {
        Boolean logoutResult = jwtTokenUtil.removeToken(userId);
        throwIf(!logoutResult,LOGOUT_ERROR);
        return true;
    }

}
