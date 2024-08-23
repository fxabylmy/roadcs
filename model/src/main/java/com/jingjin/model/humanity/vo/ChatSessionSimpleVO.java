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
public class ChatSessionSimpleVO implements Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * 会话名
     */
    private String name;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    /**
     * 串行版本uid
     */
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
