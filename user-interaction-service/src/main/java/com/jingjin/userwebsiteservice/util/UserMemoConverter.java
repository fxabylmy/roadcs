package com.jingjin.userwebsiteservice.util;

import com.jingjin.model.thirdPartyWebsite.po.ThirdPartyWebsite;
import com.jingjin.model.thirdPartyWebsite.vo.ThirdPartyWebsiteSimpleVO;
import com.jingjin.model.userInteraction.po.UserMemo;
import com.jingjin.model.userInteraction.vo.UserMemoVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMemoConverter {
    UserMemoConverter INSTANCE = Mappers.getMapper(UserMemoConverter.class);

    List<UserMemoVO> toUserMemoVOList(List<UserMemo> list);
}
