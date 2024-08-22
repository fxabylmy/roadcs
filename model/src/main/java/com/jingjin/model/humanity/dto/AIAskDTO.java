package com.jingjin.model.humanity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * aiask dto
 *
 * @author fxab
 * @date 2024/08/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AIAskDTO implements Serializable {

    /**
     * 问题
     */
    private String question;

    /**
     * 聊天会话id(为0时则为新会话)
     */
    private Integer chatSessionId;

    /**
     * 串行版本uid
     */
    private static final long serialVersionUID = 1L;
}
