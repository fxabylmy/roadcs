package com.jingjin.thirdpartywebsiteservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jingjin.model.thirdPartyWebsite.dto.DeleteFavoritesDTO;
import com.jingjin.model.thirdPartyWebsite.po.UserFavorites;

import java.util.List;

public interface UserFavoritesService extends IService<UserFavorites> {
    List<Integer> getWebsiteIdsByUserId(String userId);

    Boolean removeByWebsiteId(DeleteFavoritesDTO deleteFavoritesDTO);
}
