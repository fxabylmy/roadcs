package com.jingjin.humanityservice.controller;

import com.jingjin.common.exception.ThrowUtils;
import com.jingjin.common.result.ErrorCode;
import com.jingjin.common.utils.UserContext;
import com.jingjin.humanityservice.service.AIChatService;
import com.jingjin.model.humanity.dto.AIAskDTO;
import com.jingjin.model.humanity.po.ChatSession;
import io.reactivex.Flowable;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;



@RefreshScope // 自动配置更新
@RestController
@RequestMapping("/chat")
@Slf4j
@Tag(name = "人文接口文档", description = "人文模块接口文档")
public class AIChatController {

    @Resource
    private AIChatService aiChatService;

    /**
     * 流提问
     *
     * @param question      问题
     * @param chatSessionId 聊天会话id
     * @return {@link Flux}<{@link ServerSentEvent}<{@link String}>>
     * @throws Exception 例外
     */
    @Transactional
    @GetMapping(value = "/streamAsk",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> streamAsk(@RequestParam String question,
                                                   @RequestParam Integer chatSessionId) throws Exception {
        String userId = UserContext.getUserId();
        if (chatSessionId==0){
            return aiChatService.streamNewAsk(userId,question);
        }
        ChatSession chatSession = aiChatService.getById(chatSessionId);
        ThrowUtils.throwIf(!userId.equals(chatSession.getUserId()), ErrorCode.PRTMISSION_ERROR);
        return aiChatService.streamAsk(userId,question,chatSessionId);
    }




}
