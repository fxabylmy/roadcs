package com.jingjin.userservice.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingjin.common.exception.BusinessException;
import com.jingjin.common.result.ErrorCode;

import com.jingjin.jwtutil.jwtUtil.JwtTokenUtil;
import com.jingjin.model.user.dto.user.UserRegisterDTO;
import com.jingjin.model.user.pojo.User;
import com.jingjin.serviceClient.service.order.OrderFeignClient;
import com.jingjin.userservice.mapper.UserMapper;
import com.jingjin.userservice.service.UserService;
import com.jingjin.userservice.util.UploadUtil;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.Map;

import static com.jingjin.common.constant.RabbitMQConstant.DEMO_MESSAGE_EXCHANGE;
import static com.jingjin.common.constant.RabbitMQConstant.DEMO_MESSAGE_SEND_KEY;
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
        //todo 3.验证码验证
        //4.写入数据库
        User user = User.builder().email(email).password(digestPassword).build();
        int insert = userMapper.insert(user);
        if (insert <= 0) {
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
     * 用户登录
     *
     * @param account  帐户
     * @param password 密码
     * @return {@link Map}<{@link String}, {@link Object}>
     */
    @Override
    public Map<String, Object> userLogin(String account, String password) {
        //1.参数校验
        if (StringUtils.isAnyBlank(account, password)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        //2.加密
        String digestPassword = DigestUtil.md5Hex(SALT + password);
        // 查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", account);
        queryWrapper.eq("password", digestPassword);
        User user = userMapper.selectOne(queryWrapper);
        // 用户不存在
        if (user == null) {
            log.info("登陆失败，用户不存在");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在或密码错误");
        }
        // 3. 记录用户的Id
        Map<String, Object> tokenMap = jwtTokenUtil
                .generateTokenAndRefreshToken(String.valueOf(user.getId()),user.getId());
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
