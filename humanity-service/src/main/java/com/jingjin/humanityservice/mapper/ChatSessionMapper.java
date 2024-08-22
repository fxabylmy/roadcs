package com.jingjin.humanityservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jingjin.model.humanity.po.ChatSession;
import com.jingjin.model.thirdPartyWebsite.po.ThirdPartyWebsite;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChatSessionMapper extends BaseMapper<ChatSession> {

}
