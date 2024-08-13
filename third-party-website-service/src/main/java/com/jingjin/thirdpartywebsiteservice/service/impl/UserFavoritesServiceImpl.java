package com.jingjin.thirdpartywebsiteservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingjin.common.exception.ThrowUtils;
import com.jingjin.common.result.ErrorCode;
import com.jingjin.model.thirdPartyWebsite.dto.DeleteFavoritesDTO;
import com.jingjin.model.thirdPartyWebsite.po.ThirdPartyWebsite;
import com.jingjin.model.thirdPartyWebsite.po.UserFavorites;
import com.jingjin.thirdpartywebsiteservice.mapper.UserFavoritesMapper;
import com.jingjin.thirdpartywebsiteservice.service.UserFavoritesService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserFavoritesServiceImpl extends ServiceImpl<UserFavoritesMapper, UserFavorites> implements UserFavoritesService {
    @Resource
    private UserFavoritesMapper userFavoritesMapper;

    @Override
    public List<Integer> getWebsiteIdsByUserId(String userId) {
        LambdaQueryWrapper<UserFavorites> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFavorites::getUserId,userId)
                .select(UserFavorites::getAdminWebsiteId);
        List<UserFavorites> list = list(wrapper);
        List<Integer> ids = new ArrayList<>();
        list.stream().forEach((UserFavorites userFavorites)->{
            ids.add(userFavorites.getAdminWebsiteId());
        });
        return ids;
    }

    @Override
    public Boolean removeByWebsiteId(DeleteFavoritesDTO deleteFavoritesDTO) {
        LambdaQueryWrapper<UserFavorites> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserFavorites::getAdminWebsiteId,deleteFavoritesDTO.getAdminWebsiteId());
        Boolean isSuccess = remove(wrapper);
        return isSuccess;
    }
}
