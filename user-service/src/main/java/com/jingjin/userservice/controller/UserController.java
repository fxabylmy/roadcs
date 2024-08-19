package com.jingjin.userservice.controller;

import com.jingjin.common.result.BaseResult;
import com.jingjin.common.result.ErrorCode;
import com.jingjin.common.result.ResultUtil;
import com.jingjin.model.user.dto.UserLoginRequest;
import com.jingjin.model.user.dto.user.UploadAvatarDTO;
import com.jingjin.model.user.dto.user.UploadBackgroundDTO;
import com.jingjin.model.user.dto.user.UserRegisterDTO;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
     * 登录
     *
     * @param userLoginRequest 用户登录请求
     * @return {@link BaseResult}<{@link Map}<{@link String}, {@link Object}>>
     */
    @Operation(summary = "用户统一登录接口")
    @PostMapping("/login")
    public BaseResult<Map<String, Object>> Login(@RequestBody UserLoginRequest userLoginRequest){
        String account = userLoginRequest.getAccount();
        String password = userLoginRequest.getPassword();
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
    public BaseResult<Map<String, Object>> Logout(){
        //todo 从token获取userId
        String userId = "bc4444cd8c686efd581469d4313b9123";
        Boolean logoutResult = userService.logout(userId);
        if (logoutResult){
            return ResultUtil.success();
        }
        return ResultUtil.error(SYSTEM_ERROR);
    }

    @Operation(summary = "用户更改头像")
    @PutMapping("/avatar/upload")
    @Transactional
    public BaseResult<String> uploadAvatar(UploadAvatarDTO uploadAvatarDTO) throws Exception {
        //todo  从token获取userId
        String userId = "bc4444cd8c686efd581469d4313b9123";
        Boolean isSuccess = userService.uploadAvatar(uploadAvatarDTO.getAvatar(),userId);
        return isSuccess?ResultUtil.success("头像上传成功"):ResultUtil.error(SYSTEM_ERROR);
    }

    @Operation(summary = "用户获取头像")
    @GetMapping("/avatar/get")
    @Transactional
    public ResponseEntity<ByteArrayResource> getAvatar() throws IOException {
        //todo  从token获取userId
        String userId = "bc4444cd8c686efd581469d4313b9123";
        byte[] imageBytes = userService.getAvatar(userId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        ByteArrayResource avatar = new ByteArrayResource(imageBytes);
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(imageBytes.length)
                .body(avatar);
        }

    @Operation(summary = "用户更改背景")
    @PutMapping("/background/upload")
    @Transactional
    public BaseResult<String> uploadBackground(UploadBackgroundDTO uploadBackgroundDTO) throws Exception {
        //todo  用户更改背景
        return ResultUtil.success("背景上传成功");
    }

    @Operation(summary = "用户获取背景")
    @GetMapping("/background/get")
    @Transactional
    public ResponseEntity<ByteArrayResource> getBackground() throws IOException {
        //todo  用户获取背景
        return null;
    }

    @Operation(summary = "获取用户详情")
    @GetMapping("/user/get")
    @Transactional
    public BaseResult<UserDetailVO> getUserDetail() throws IOException {
        //todo  获取用户详情
        UserDetailVO userDetailVO = new UserDetailVO();
        return ResultUtil.success(userDetailVO);
    }









}
