package com.jingjin.adminwebsiteservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingjin.adminwebsiteservice.mapper.AdminWebsiteMapper;
import com.jingjin.adminwebsiteservice.service.AdminWebsiteService;
import com.jingjin.model.adminWebsite.po.AdminWebsite;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AdminWebsiteServiceImpl extends ServiceImpl<AdminWebsiteMapper, AdminWebsite> implements AdminWebsiteService{

    @Resource
    private AdminWebsiteMapper adminWebsiteMapper;
}
