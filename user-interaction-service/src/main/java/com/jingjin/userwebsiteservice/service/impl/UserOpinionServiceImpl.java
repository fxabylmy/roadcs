package com.jingjin.userwebsiteservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingjin.model.userInteraction.po.UserOpinion;
import com.jingjin.userwebsiteservice.mapper.UserOpinionMapper;
import com.jingjin.userwebsiteservice.service.UserOpinionService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserOpinionServiceImpl extends ServiceImpl<UserOpinionMapper, UserOpinion> implements UserOpinionService {

    @Resource
    private UserOpinionMapper userOpinionMapper;
}
