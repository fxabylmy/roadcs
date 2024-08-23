package com.jingjin.humanityservice.controller;

import com.jingjin.common.exception.ThrowUtils;
import com.jingjin.common.result.BaseResult;
import com.jingjin.common.result.ErrorCode;
import com.jingjin.common.result.ResultUtil;
import com.jingjin.common.utils.UserContext;
import com.jingjin.humanityservice.service.AIChatService;
import com.jingjin.model.humanity.dto.AIAskDTO;
import com.jingjin.model.humanity.dto.UpdateSessionNameDTO;
import com.jingjin.model.humanity.po.ChatMessage;
import com.jingjin.model.humanity.po.ChatSession;
import com.jingjin.model.humanity.vo.ChatMessageSimpleVO;
import com.jingjin.model.humanity.vo.ChatSessionSimpleVO;
import com.jingjin.model.thirdPartyWebsite.vo.ThirdPartyWebsiteSimpleVO;
import io.reactivex.Flowable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;


/**
 * AI问答控制器
 *
 * @author fxab
 * @date 2024/08/23
 */
@RefreshScope // 自动配置更新
@RestController
@RequestMapping("/chat")
@Slf4j
@Tag(name = "人文接口文档", description = "人文模块接口文档")
public class AIChatController {

    /**
     * ai聊天服务
     */
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
    @Operation(summary = "流提问")
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

    /**
     * 获取聊天会话列表
     *
     * @return {@link BaseResult}<{@link List}<{@link ChatSessionSimpleVO}>>
     */
    @Operation(summary = "获取AI聊天会话列表(不包括聊天记录)")
    @GetMapping("/get/list")
    @Transactional
    public BaseResult<List<ChatSessionSimpleVO>> getChatSessionList(){
        String userId = UserContext.getUserId();
        List<ChatSessionSimpleVO> sessionSimpleVOList = aiChatService.getAll(userId);
        return ResultUtil.success(sessionSimpleVOList);
    }


    @Operation(summary = "修改会话名")
    @PostMapping("/update/name")
    @Transactional
    public BaseResult<String> updateSessionName(@RequestBody UpdateSessionNameDTO updateSessionNameDTO){
        String userId = UserContext.getUserId();
        ChatSession chatSession = aiChatService.getById(updateSessionNameDTO.getId());
        ThrowUtils.throwIf(!userId.equals(chatSession.getUserId()),ErrorCode.PRTMISSION_ERROR);
        chatSession.setName(updateSessionNameDTO.getName());
        Boolean isSuccess = aiChatService.updateById(chatSession);
        return isSuccess?ResultUtil.success("修改会话名成功"):ResultUtil.error(ErrorCode.SYSTEM_ERROR);
    }

    @Operation(summary = "删除会话")
    @DeleteMapping("/delete/{id}")
    @Transactional
    public BaseResult<String> deleteSession(@PathVariable("id") Integer ChatSessionId){
        String userId = UserContext.getUserId();
        ChatSession chatSession = aiChatService.getById(ChatSessionId);
        ThrowUtils.throwIf(!userId.equals(chatSession.getUserId()),ErrorCode.PRTMISSION_ERROR);
        Boolean isSuccess = aiChatService.removeSessionById(ChatSessionId);
        return isSuccess?ResultUtil.success("删除会话成功"):ResultUtil.error(ErrorCode.SYSTEM_ERROR);
    }



    /**
     * 获取聊天消息
     *
     * @param ChatSessionId 聊天会话id
     * @return {@link BaseResult}<{@link List}<{@link ChatMessageSimpleVO}>>
     */
    @Operation(summary = "获取会话的聊天记录")
    @GetMapping("/get/message/{id}")
    @Transactional
    public BaseResult<List<ChatMessageSimpleVO>> getChatMessage(@PathVariable("id") Integer ChatSessionId){
        String userId = UserContext.getUserId();
        ChatSession chatSession = aiChatService.getById(ChatSessionId);
        ThrowUtils.throwIf(!userId.equals(chatSession.getUserId()),ErrorCode.PRTMISSION_ERROR);
        List<ChatMessageSimpleVO> messageSimpleVOList = aiChatService.getMessage(ChatSessionId);
        return ResultUtil.success(messageSimpleVOList);
    }




}
