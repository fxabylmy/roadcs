package com.jingjin.humanityservice.util;


import com.alibaba.dashscope.aigc.generation.GenerationParam;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * AI问答工具类
 *
 * @author fxab
 * @date 2024/07/08
 */
@Component
@RefreshScope
@Slf4j
public class ChatUtil {


    /**
     * api密钥
     */
    @Value("${aliyun.chat.access-key:''}")
    public String ApiKey;

    /**
     * 创建消息
     *
     * @param role    角色
     * @param content 内容
     * @return {@link Message}
     */
    public static Message createMessage(Role role, String content) {
        return Message.builder().role(role.getValue()).content(content).build();
    }

    /**
     * 创建生成参数
     *
     * @param messages 消息
     * @return {@link GenerationParam}
     */
    public GenerationParam createGenerationParam(List<Message> messages) {
        return GenerationParam.builder()
                //选用模型
                .model("qwen-long")
                //对话信息
                .messages(messages)
                //返回结果的格式
                .resultFormat(GenerationParam.ResultFormat.MESSAGE)
                //生成过程中的核采样方法概率阈值
                .topP(0.8)
                //模型在生成文本时是否使用互联网搜索结果进行参考
                .enableSearch(true)
                //限制回答的最大token数，1token=1.5汉字
                .maxTokens(200)
                //api密钥
                .apiKey(ApiKey)
                //控制在流式输出模式下是否开启增量输出
                .incrementalOutput(true)
                .build();
    }


}
