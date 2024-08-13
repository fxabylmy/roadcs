package com.example.adminmanageservice.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.adminmanageservice.mapper.ConfigMapper;
import com.example.adminmanageservice.service.ConfigService;
import com.jingjin.model.adminManage.po.Config;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {

    @Resource
    private ConfigMapper configMapper;
}
