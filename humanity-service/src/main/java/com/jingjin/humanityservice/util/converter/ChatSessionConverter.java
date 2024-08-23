package com.jingjin.humanityservice.util.converter;

import com.jingjin.model.humanity.po.ChatSession;
import com.jingjin.model.humanity.vo.ChatSessionSimpleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ChatSessionConverter {

    ChatSessionConverter INSTANCE = Mappers.getMapper(ChatSessionConverter.class);

    List<ChatSessionSimpleVO> toChatSessionSimpleVOList(List<ChatSession> list);

}
