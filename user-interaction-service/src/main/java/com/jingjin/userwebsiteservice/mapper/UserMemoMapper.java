package com.jingjin.userwebsiteservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jingjin.model.userInteraction.po.UserMemo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMemoMapper extends BaseMapper<UserMemo> {
}
