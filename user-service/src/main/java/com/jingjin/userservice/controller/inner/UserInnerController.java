package com.jingjin.userservice.controller.inner;

import com.jingjin.model.user.po.User;
import com.jingjin.serviceClient.service.user.UserFeignClient;
import com.jingjin.userservice.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户内部控制器
 *
 * @author fxab
 * @date 2024/07/22
 */
@RestController
@RequestMapping("/inner")
@Slf4j
public class UserInnerController implements UserFeignClient {

    /**
     * 用户服务
     */
    @Resource
    private UserService userService;

    /**
     * 按id获取用户
     *
     * @param userId 用户id
     * @return {@link User}
     */
    @Override
    public User getUserById(String userId) {
        User user = userService.getById(userId);
        return user;
    }
}
