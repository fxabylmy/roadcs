package com.jingjin.humanityservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jingjin.model.humanity.po.ChatSession;
import com.jingjin.model.humanity.vo.ChatMessageSimpleVO;
import com.jingjin.model.humanity.vo.ChatSessionSimpleVO;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;

import java.util.List;

public interface AIChatService extends IService<ChatSession> {

    Flux<ServerSentEvent<String>> streamNewAsk(String userId, String aiAskDTO)throws Exception ;

    Flux<ServerSentEvent<String>> streamAsk(String userId, String question, Integer chatSessionId)throws Exception;

    List<ChatSessionSimpleVO> getAll(String userId);

    List<ChatMessageSimpleVO> getMessage(Integer chatSessionId);

    Boolean removeSessionById(Integer chatSessionId);
}
