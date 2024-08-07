package com.jingjin.model.adminWebsite.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 管理员网站
 *
 * @author fxab
 * @date 2024/08/07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminWebsite implements Serializable {


    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO) //指定id类型为自增长
    private Integer id;

    /**
     * 网站名
     */
    private String name;

    /**
     * 徽标url
     */
    private String logoUrl;

    /**
     * 网站url
     */
    private String websiteUrl;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 知识类别
     */
    private Integer category;

    /**
     * 重要性级别
     */
    private Integer importanceLevel;

    /**
     * 副标题
     */
    private String subtitle;

    /**
     * 建议
     */
    private String recommendation;

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
