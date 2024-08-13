package com.jingjin.thirdpartywebsiteservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingjin.thirdpartywebsiteservice.mapper.ThirdPartyWebsiteMapper;
import com.jingjin.thirdpartywebsiteservice.service.ThirdPartyWebsiteService;
import com.jingjin.model.thirdPartyWebsite.po.ThirdPartyWebsite;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ThirdPartyWebsiteServiceImpl extends ServiceImpl<ThirdPartyWebsiteMapper, ThirdPartyWebsite> implements ThirdPartyWebsiteService {

    @Resource
    private ThirdPartyWebsiteMapper thirdPartyWebsiteMapper;
}
