package com.jingjin.humanityservice.util.converter;

import com.jingjin.model.humanity.po.ChatMessage;
import com.jingjin.model.humanity.po.ChatSession;
import com.jingjin.model.humanity.vo.ChatMessageSimpleVO;
import com.jingjin.model.humanity.vo.ChatSessionSimpleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ChatMessageConverter {
    ChatMessageConverter INSTANCE = Mappers.getMapper(ChatMessageConverter.class);

    List<ChatMessageSimpleVO> toChatMessageSimpleVOList(List<ChatMessage> list);
}
