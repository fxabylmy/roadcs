package com.jingjin.model.userInteraction.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RespondedUserOpinionVO implements Serializable {

    /**
     * id
     */
    private Integer id;

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 回复者姓名
     */
    private String responseName;

    /**
     * 回复内容
     */
    private String responseContent;
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
    private static final long serialVersionUID = 1L;
}

