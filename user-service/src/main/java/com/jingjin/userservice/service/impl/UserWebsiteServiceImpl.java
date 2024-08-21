package com.jingjin.userservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingjin.model.userInteraction.po.UserWebsite;
import com.jingjin.userservice.mapper.UserWebsiteMapper;
import com.jingjin.userservice.service.UserWebsiteService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserWebsiteServiceImpl extends ServiceImpl<UserWebsiteMapper, UserWebsite> implements UserWebsiteService {

    @Resource
    private UserWebsiteMapper userWebsiteMapper;
}
