package com.jingjin.userwebsiteservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingjin.model.adminWebsite.po.AdminWebsite;
import com.jingjin.model.userWebsite.po.UserWebsite;
import com.jingjin.userwebsiteservice.mapper.UserWebsiteMapper;
import com.jingjin.userwebsiteservice.service.UserWebsiteService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserWebsiteServiceImpl extends ServiceImpl<UserWebsiteMapper, UserWebsite> implements UserWebsiteService {

    @Resource
    private UserWebsiteMapper userWebsiteMapper;
}
