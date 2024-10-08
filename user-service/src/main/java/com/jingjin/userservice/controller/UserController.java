package com.jingjin.userservice.controller;

import com.jingjin.common.exception.ThrowUtils;
import com.jingjin.common.result.BaseResult;
import com.jingjin.common.result.ErrorCode;
import com.jingjin.common.result.ResultUtil;
import com.jingjin.common.utils.UserContext;
import com.jingjin.model.user.dto.user.UserLoginDTO;
import com.jingjin.model.user.dto.user.*;
import com.jingjin.model.user.po.User;
import com.jingjin.model.user.vo.UserDetailVO;
import com.jingjin.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

import static com.jingjin.common.result.ErrorCode.SYSTEM_ERROR;

/**
 * 用户服务控制层
 *
 * @author fxab
 * @date 2024/08/06
 */
@RefreshScope // 自动配置更新
@RestController
@RequestMapping("/")
@Slf4j
@Tag(name = "用户接口文档", description = "用户中心模块接口文档")
public class UserController {

    /**
     * 用户服务
     */
    @Resource
    private UserService userService;

    /**
     * 发送邮箱验证码
     *
     * @param email 对应邮箱
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "发送邮箱验证码")
    @GetMapping("/sendEmail")
    public BaseResult<String> sendEmail(String email){
        Boolean isSuccess = userService.sendEmail(email);
        return isSuccess?ResultUtil.success("邮箱验证码发送成功"):ResultUtil.error(ErrorCode.SENDEMAIL_ERROR);
    }

    /**
     * 确认邮箱验证码
     * @param userEmailConfirmDTO 邮箱验证码对应DTO
     * @return 正确与否
     */
    @Operation(summary = "邮箱验证码验证")
    @PostMapping("/confirmEmail")
    public BaseResult<String> emailConfirm(@RequestBody UserEmailConfirmDTO userEmailConfirmDTO){
        String email = userEmailConfirmDTO.getEmail();
        String emailCode = userEmailConfirmDTO.getEmailCode();
        Boolean isSuccess = userService.confirmEmail(email, emailCode);
        return isSuccess?ResultUtil.success("验证成功"):ResultUtil.error(ErrorCode.REGISTER);
    }

    /**
     * 注册
     *
     * @param userRegisterDTO 用户注册dto
     * @return {@link BaseResult}<{@link String}>
     */
    @Operation(summary = "用户邮箱注册")
    @PostMapping("/register")
    public BaseResult<String> Register(@RequestBody UserRegisterDTO userRegisterDTO){
        Boolean isSuccess = userService.userRegister(userRegisterDTO);
        return isSuccess?ResultUtil.success("注册成功"):ResultUtil.error(ErrorCode.REGISTER);
    }

    /**
     * 用户重置密码接口
     * @param uploadPasswordDTO 新密码重置DTO
     */
    @Operation(summary = "用户重置密码接口")
    @PostMapping("/passwordReWrite")
    public BaseResult<String> passwordReWrite(@RequestBody UploadPasswordDTO uploadPasswordDTO){
        Boolean isSuccess = userService.passwordReWrite(uploadPasswordDTO);
        return isSuccess?ResultUtil.success("密码重置成功"):ResultUtil.error(ErrorCode.REGISTER);
    }

    /**
     * 登录
     *
     * @param userLoginDTO 用户登录请求DTO
     * @return {@link BaseResult}<{@link Map}<{@link String}, {@link Object}>>
     */
    @Operation(summary = "用户统一登录接口")
    @PostMapping("/login")
    public BaseResult<Map<String, Object>> Login(@RequestBody UserLoginDTO userLoginDTO){
        String account = userLoginDTO.getAccount();
        String password = userLoginDTO.getPassword();
        Map<String, Object> tokenMap = userService.userLogin(account,password);
        return ResultUtil.success(tokenMap);
    }


    /**
     * 刷新令牌
     *
     * @param refreshToken 刷新令牌
     * @return {@link BaseResult}<{@link Map}<{@link String}, {@link Object}>>
     */
    @Operation(summary = "令牌刷新")
    @PostMapping("/token/refresh")
    public BaseResult<Map<String, Object>> RefreshToken(@RequestHeader(value = "${auth.jwt.header}") String refreshToken){
        Map<String, Object> tokenMap = userService.refreshToken(refreshToken);
        return ResultUtil.success(tokenMap);
    }

    /**
     * 注销
     *
     * @return {@link BaseResult}<{@link Map}<{@link String}, {@link Object}>>
     */
    @Operation(summary = "用户登出")
    @PostMapping("/logout")
    public BaseResult<String> Logout(){
        // 从token获取userId
        String userId = UserContext.getUserId();
        Boolean logoutResult = userService.logout(userId);
        if (logoutResult){
            return ResultUtil.success("发送成功");
        }
        return ResultUtil.error(SYSTEM_ERROR);
    }

    @Operation(summary = "用户更改头像")
    @PutMapping("/avatar/upload")
    @Transactional
    public BaseResult<String> uploadAvatar(UploadAvatarDTO uploadAvatarDTO) throws Exception {
        // 从token获取userId
        String userId = UserContext.getUserId();
        Boolean isSuccess = userService.uploadAvatar(uploadAvatarDTO.getAvatar(),userId);
        return isSuccess?ResultUtil.success("头像上传成功"):ResultUtil.error(SYSTEM_ERROR);
    }

    @Operation(summary = "用户获取头像")
    @GetMapping("/avatar/get")
    @Transactional
    public BaseResult<String> getAvatar() throws IOException {
        // 从token获取userId
        String userId = UserContext.getUserId();
        String avatarUrl = userService.getById(userId).getAvatarUrl();
        ThrowUtils.throwIf(avatarUrl==null,ErrorCode.GONE_ERROR);
        return ResultUtil.success(avatarUrl);
        }

    @Operation(summary = "用户更改为自定义背景")
    @PutMapping("/background/custom/upload")
    @Transactional
    public BaseResult<String> uploadCustomBackground(UploadCustomBackgroundDTO uploadCustomBackgroundDTO) throws Exception {
        String userId = UserContext.getUserId();
        String backgroundUrl = userService.uploadBackground(uploadCustomBackgroundDTO.getBackground(),userId);
        return ResultUtil.success(backgroundUrl);
    }

    @Operation(summary = "用户更改为预设背景")
    @PutMapping("/background/presuppose/upload")
    @Transactional
    public BaseResult<String> uploadPresupposeBackground(UploadPresupposeBackgroundDTO uploadPresupposeBackgroundDTO) throws Exception {
        String userId = UserContext.getUserId();
        String backgroundUrl = uploadPresupposeBackgroundDTO.getBackgroundUrl();
        User user = User.builder().id(userId).backgroundUrl(backgroundUrl).build();
        return ResultUtil.success(backgroundUrl);
    }

    @Operation(summary = "用户获取背景")
    @GetMapping("/background/get")
    @Transactional
    public BaseResult<String> getBackground() throws IOException {
        // 从token获取userId
        String userId = UserContext.getUserId();
        String backgroundUrl = userService.getById(userId).getBackgroundUrl();
        ThrowUtils.throwIf(backgroundUrl==null,ErrorCode.GONE_ERROR);
        return ResultUtil.success(backgroundUrl);
    }

    @Operation(summary = "获取用户详情")
    @GetMapping("/user/get")
    @Transactional
    public BaseResult<UserDetailVO> getUserDetail() throws IOException {
        String userId = UserContext.getUserId();
        UserDetailVO userDetailVO = userService.getUserDetail(userId);
        return ResultUtil.success(userDetailVO);
    }

    @Operation(summary = "修改用户信息")
    @PutMapping("/user/update")
    @Transactional
    public BaseResult<String> updateUser(@RequestBody UpdateUserDTO updateUserDTO) throws IOException {
        String userId = UserContext.getUserId();
        User user = User.builder()
                .id(userId)
                .name(updateUserDTO.getName())
                .sex(updateUserDTO.getSex())
                .signature(updateUserDTO.getSignature())
                .build();
        Boolean isSuccess = userService.updateById(user);
        return isSuccess? ResultUtil.success("修改用户信息成功"):ResultUtil.error(ErrorCode.SYSTEM_ERROR);
    }







}
