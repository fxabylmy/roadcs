package com.jingjin.userservice.util.converter;

import com.jingjin.model.user.po.User;
import com.jingjin.model.user.vo.UserDetailVO;
import com.jingjin.model.userInteraction.po.UserWebsite;
import com.jingjin.model.userInteraction.vo.UserWebsiteVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserWebsiteConverter {

    UserWebsiteConverter INSTANCE = Mappers.getMapper(UserWebsiteConverter.class);

    UserWebsiteVO toUserWebsiteVO(UserWebsite userWebsite);

}
