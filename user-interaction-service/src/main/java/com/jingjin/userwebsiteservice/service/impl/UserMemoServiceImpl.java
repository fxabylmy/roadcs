package com.jingjin.userwebsiteservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingjin.model.userInteraction.po.UserMemo;
import com.jingjin.userwebsiteservice.mapper.UserMemoMapper;
import com.jingjin.userwebsiteservice.service.UserMemoService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserMemoServiceImpl extends ServiceImpl<UserMemoMapper, UserMemo> implements UserMemoService {

    @Resource
    private UserMemoMapper userMemoMapper;
}
