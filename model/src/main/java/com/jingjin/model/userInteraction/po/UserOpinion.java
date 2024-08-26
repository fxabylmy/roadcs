package com.jingjin.model.userInteraction.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户意见
 *
 * @author fxab
 * @date 2024/08/08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOpinion implements Serializable {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO) //指定id类型为自增长
    private Integer id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 回复者id
     */
    private String responseId;

    /**
     * 回复内容
     */
    private String responseContent;

    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 逻辑删除(0不删除,1删除)
     */
    @TableLogic(value="0",delval="1")
    private Integer isDelete;

    /**
     * 串行版本uid
     */
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}

