package com.jingjin.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingjin.common.result.PageResponse;
import com.jingjin.common.result.PageUtil;
import com.jingjin.model.thirdPartyWebsite.vo.ThirdPartyWebsiteSimpleVO;
import com.jingjin.model.userInteraction.dto.AddUserWebsiteDTO;
import com.jingjin.model.userInteraction.dto.UpdateUserWebsiteDTO;
import com.jingjin.model.userInteraction.po.UserOpinion;
import com.jingjin.model.userInteraction.po.UserWebsite;
import com.jingjin.model.userInteraction.vo.UserOpinionVO;
import com.jingjin.model.userInteraction.vo.UserWebsiteVO;
import com.jingjin.userservice.mapper.UserWebsiteMapper;
import com.jingjin.userservice.service.UserWebsiteService;
import com.jingjin.userservice.util.converter.UserOpinionConverter;
import com.jingjin.userservice.util.converter.UserWebsiteConverter;
import com.jingjin.userservice.util.upload.UploadUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.jingjin.common.utils.urlUtil.UrlUtil.urlToBase64;

@Service
@Slf4j
public class UserWebsiteServiceImpl extends ServiceImpl<UserWebsiteMapper, UserWebsite> implements UserWebsiteService {

    @Resource
    private UserWebsiteMapper userWebsiteMapper;

    @Resource
    private UploadUtil uploadUtil;

    @Override
    public Boolean addUserWebsite(String userId, AddUserWebsiteDTO addUserWebsiteDTO) {
        String logoUrl = uploadUtil.uploadImg(addUserWebsiteDTO.getLogo());
        UserWebsite userWebsite = UserWebsite.builder()
                .userId(userId)
                .name(addUserWebsiteDTO.getName())
                .websiteUrl(addUserWebsiteDTO.getWebsiteUrl())
                .logoUrl(logoUrl)
                .subtitle(addUserWebsiteDTO.getSubtitle())
                .build();
        Boolean isSuccess = save(userWebsite);
        return isSuccess;
    }

    @Override
    public Boolean updateUserWebsite(UpdateUserWebsiteDTO updateUserWebsiteDTO) {
        String logoUrl = uploadUtil.uploadImg(updateUserWebsiteDTO.getLogo());
        UserWebsite userWebsite = UserWebsite.builder()
                .id(updateUserWebsiteDTO.getId())
                .name(updateUserWebsiteDTO.getName())
                .websiteUrl(updateUserWebsiteDTO.getWebsiteUrl())
                .logoUrl(logoUrl)
                .subtitle(updateUserWebsiteDTO.getSubtitle())
                .build();
        Boolean isSuccess = updateById(userWebsite);
        return isSuccess;
    }

    @Override
    public PageResponse<UserWebsiteVO> getPageByUserId(String userId, int pageIndex, int pageSize) {
        IPage<UserWebsite> pageParams = new Page<>(pageIndex, pageSize);
        LambdaQueryWrapper<UserWebsite> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserWebsite::getIsDelete,"0")
                .eq(UserWebsite::getUserId,userId);
        IPage<UserWebsite> resultPage =  page(pageParams,wrapper);
        if(pageParams.getTotal()==0)
            return PageUtil.isNull(new ArrayList<UserWebsiteVO>());
        List<UserWebsite> userWebsiteList = resultPage.getRecords();
        List<UserWebsiteVO> userWebsiteVOList = new ArrayList<>();
        userWebsiteList.stream().forEach((UserWebsite userWebsite)->{
            String base64Image = urlToBase64(userWebsite.getLogoUrl());
            UserWebsiteVO userWebsiteVO =
                    UserWebsiteConverter.INSTANCE.toUserWebsiteVO(userWebsite);
            userWebsiteVO.setLogoBase64(base64Image);
            userWebsiteVOList.add(userWebsiteVO);
        });
        PageResponse<UserWebsiteVO> pageResponse = PageUtil.setPage(resultPage,userWebsiteVOList);
        return pageResponse;

    }
}
