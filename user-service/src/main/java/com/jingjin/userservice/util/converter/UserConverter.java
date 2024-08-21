package com.jingjin.userservice.util.converter;

import com.jingjin.model.user.po.User;
import com.jingjin.model.user.vo.UserDetailVO;
import com.jingjin.model.userInteraction.po.UserMemo;
import com.jingjin.model.userInteraction.vo.UserMemoVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserConverter {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    UserDetailVO toUserDetailVO(User user);
}
