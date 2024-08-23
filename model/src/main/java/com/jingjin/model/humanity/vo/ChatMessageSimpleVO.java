package com.jingjin.model.humanity.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageSimpleVO implements Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * 发件人类型(1为用户，2为AI)
     */
    private Integer senderType;

    /**
     * 信息
     */
    private String message;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 串行版本uid
     */
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
