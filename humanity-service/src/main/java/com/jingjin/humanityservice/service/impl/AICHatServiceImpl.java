package com.jingjin.humanityservice.service.impl;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jingjin.humanityservice.mapper.ChatMessageMapper;
import com.jingjin.humanityservice.mapper.ChatSessionMapper;
import com.jingjin.humanityservice.service.AIChatService;
import com.jingjin.model.humanity.dto.AIAskDTO;
import com.jingjin.model.humanity.po.ChatMessage;
import com.jingjin.model.humanity.po.ChatSession;
import com.jingjin.model.thirdPartyWebsite.po.UserFavorites;
import io.reactivex.Flowable;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.jingjin.humanityservice.util.ChatUtil.createGenerationParam;
import static com.jingjin.humanityservice.util.ChatUtil.createMessage;

/**
 * aichat服务实施
 *
 * @author fxab
 * @date 2024/08/22
 */
@Service
@Slf4j
public class AICHatServiceImpl extends ServiceImpl<ChatSessionMapper, ChatSession> implements AIChatService {

    /**
     * 聊天会话映射器
     */
    @Resource
    private ChatSessionMapper chatSessionMapper;

    /**
     * 聊天消息映射器
     */
    @Resource
    private ChatMessageMapper chatMessageMapper;


    @Override
    public Flux<ServerSentEvent<String>> streamNewAsk(String userId, String question) throws Exception {
        Generation gen = new Generation();
        List<String> responseData = new ArrayList<>();
        List<Message> messages = new ArrayList<>();
        messages.add(createMessage(Role.SYSTEM, "You are a helpful assistant."));
        messages.add(createMessage(Role.USER,question));
        GenerationParam param = createGenerationParam(messages);
        // 调用生成接口，获取Flowable对象
        Flowable<GenerationResult> result = gen.streamCall(param);
        // 将Flowable转换成Flux<ServerSentEvent<String>>并进行处理
        return Flux.from(result)
                .delayElements(Duration.ofMillis(500))
                .map(message -> {
                    String output = message.getOutput().getChoices().get(0).getMessage().getContent();
                    String replaceOutput = output.replace("\n","\\x0A");
                    responseData.add(output);
                    return ServerSentEvent.<String>builder()
                            .data(replaceOutput)
                            .build();
                })
                .concatWith(Flux.just(ServerSentEvent.<String>builder().comment("").build()))
                .doOnComplete(()-> {
                    String combinedResult = String.join("",responseData);
                    ChatSession chatSession = ChatSession.builder().userId(userId).build();
                    chatSessionMapper.insert(chatSession);
                    ChatMessage ackMessage = ChatMessage.builder()
                            .sessionId(chatSession.getId())
                            .senderType(1)
                            .message(question)
                            .build();
                    chatMessageMapper.insert(ackMessage);
                    ChatMessage answerMessage = ChatMessage.builder()
                            .sessionId(chatSession.getId())
                            .senderType(2)
                            .message(combinedResult)
                            .build();
                    chatMessageMapper.insert(answerMessage);
                })
                .doOnError(e -> {
                    if (e instanceof NoApiKeyException) {
                        // 处理 NoApiKeyException
                    } else if (e instanceof InputRequiredException) {
                        // 处理 InputRequiredException
                    } else if (e instanceof ApiException) {
                        // 处理其他 ApiException
                    } else {
                        // 处理其他异常
                    }
                });
    }

    @Override
    public Flux<ServerSentEvent<String>> streamAsk(String userId, String question, Integer chatSessionId) throws Exception{
        Generation gen = new Generation();
        List<String> responseData = new ArrayList<>();
        List<Message> messages = new ArrayList<>();
        messages.add(createMessage(Role.SYSTEM, "You are a helpful assistant."));
        //从数据库查询历史对话
        LambdaQueryWrapper<ChatMessage> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ChatMessage::getSessionId,chatSessionId)
                        .orderByDesc(ChatMessage::getId)
                                .last("limit 10");
        List<ChatMessage> messageList = chatMessageMapper.selectList(wrapper);
        List<ChatMessage> messageOrderList = messageList.stream()
                .sorted(Comparator.comparing(ChatMessage::getId))
                .collect(Collectors.toList());
        messageOrderList.stream().forEach((ChatMessage chatMessage)->{
            if (chatMessage.getSenderType()==1){
                messages.add(createMessage(Role.USER,chatMessage.getMessage()));
            }else {
                messages.add(createMessage(Role.ASSISTANT,chatMessage.getMessage()));
            }
        });
        messages.add(createMessage(Role.USER,question));
        GenerationParam param = createGenerationParam(messages);
        // 调用生成接口，获取Flowable对象
        Flowable<GenerationResult> result = gen.streamCall(param);
        // 将Flowable转换成Flux<ServerSentEvent<String>>并进行处理
        return Flux.from(result)
                .delayElements(Duration.ofMillis(500))
                .map(message -> {
                    String output = message.getOutput().getChoices().get(0).getMessage().getContent();
                    String output1 = output.replace("\n","\\x0A");
                    responseData.add(output);
                    return ServerSentEvent.<String>builder()
                            .data(output1)
                            .build();
                })
                .concatWith(Flux.just(ServerSentEvent.<String>builder().comment("").build()))
                .doOnComplete(()-> {
                    String combinedResult = String.join("",responseData);
                    ChatMessage ackMessage = ChatMessage.builder()
                            .sessionId(chatSessionId)
                            .senderType(1)
                            .message(question)
                            .build();
                    chatMessageMapper.insert(ackMessage);
                    ChatMessage answerMessage = ChatMessage.builder()
                            .sessionId(chatSessionId)
                            .senderType(2)
                            .message(combinedResult)
                            .build();
                    chatMessageMapper.insert(answerMessage);
                })
                .doOnError(e -> {
                    if (e instanceof NoApiKeyException) {
                        // 处理 NoApiKeyException
                    } else if (e instanceof InputRequiredException) {
                        // 处理 InputRequiredException
                    } else if (e instanceof ApiException) {
                        // 处理其他 ApiException
                    } else {
                        // 处理其他异常
                    }
                });
    }
}
