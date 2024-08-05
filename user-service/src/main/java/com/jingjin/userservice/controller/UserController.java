package com.jingjin.userservice.controller;

import com.jingjin.common.result.BaseResult;
import com.jingjin.common.result.ErrorCode;
import com.jingjin.common.result.ResultUtil;
import com.jingjin.model.user.dto.UserLoginRequest;
import com.jingjin.model.user.dto.user.UserRegisterDTO;
import com.jingjin.userservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.jingjin.common.result.ErrorCode.SYSTEM_ERROR;

@RefreshScope // 自动配置更新
@RestController
@RequestMapping("/")
@Slf4j
@Tag(name = "用户接口文档", description = "用户中心模块接口文档")
public class UserController {

    @Resource
    private UserService userService;

    @Operation(summary = "用户邮箱注册")
    @PostMapping("/register")
    public BaseResult<String> Register(@RequestBody UserRegisterDTO userRegisterDTO){
        Boolean isSuccess = userService.userRegister(userRegisterDTO);
        return isSuccess?ResultUtil.success("注册成功"):ResultUtil.error(ErrorCode.REGISTER);
    }

    @Operation(summary = "用户统一登录接口")
    @PostMapping("/login")
    public BaseResult<Map<String, Object>> Login(@RequestBody UserLoginRequest userLoginRequest){
        String account = userLoginRequest.getAccount();
        String password = userLoginRequest.getPassword();
        Map<String, Object> tokenMap = userService.userLogin(account,password);
        return ResultUtil.success(tokenMap);
    }


    @Operation(summary = "令牌刷新")
    @PostMapping("/token/refresh")
    public BaseResult<Map<String, Object>> RefreshToken(@RequestHeader(value = "${auth.jwt.header}") String refreshToken){
        Map<String, Object> tokenMap = userService.refreshToken(refreshToken);
        return ResultUtil.success(tokenMap);
    }

    @Operation(summary = "用户登出")
    @PostMapping("/logout")
    public BaseResult<Map<String, Object>> Logout(String userId){
        Boolean logoutResult = userService.logout(userId);
        if (logoutResult){
            return ResultUtil.success();
        }
        return ResultUtil.error(SYSTEM_ERROR);
    }



}
