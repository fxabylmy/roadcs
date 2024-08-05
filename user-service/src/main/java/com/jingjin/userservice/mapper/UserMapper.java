package com.jingjin.userservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.jingjin.model.user.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
