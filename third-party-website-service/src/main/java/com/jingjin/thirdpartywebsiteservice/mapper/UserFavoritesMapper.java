package com.jingjin.thirdpartywebsiteservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jingjin.model.thirdPartyWebsite.po.ThirdPartyWebsite;
import com.jingjin.model.thirdPartyWebsite.po.UserFavorites;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserFavoritesMapper extends BaseMapper<UserFavorites> {
}
