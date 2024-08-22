package com.jingjin.humanityservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jingjin.model.humanity.dto.AIAskDTO;
import com.jingjin.model.humanity.po.ChatSession;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;

public interface AIChatService extends IService<ChatSession> {

    Flux<ServerSentEvent<String>> streamNewAsk(String userId, String aiAskDTO)throws Exception ;

    Flux<ServerSentEvent<String>> streamAsk(String userId, String question, Integer chatSessionId)throws Exception;
}
