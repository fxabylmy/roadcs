package com.jingjin.orderservice.controller;

import com.jingjin.common.result.BaseResult;
import com.jingjin.common.result.ResultUtil;
import com.jingjin.model.user.po.User;
import com.jingjin.serviceClient.service.user.UserFeignClient;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class OrderController {
    @Resource
    private UserFeignClient userFeignClient;

    @GetMapping("/id")
    public BaseResult<User> UserById(@RequestParam Long userId){
        User user = userFeignClient.getUserById(userId);
        return ResultUtil.success(user);
    }
}
