package com.jingjin.model.user.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 *
 * @author fxab
 * @date 2024/07/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_UUID) //指定id类型为随机生成uuid
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话
     */
    private String phone;

    /**
     * 微信
     */
    private String wechat;

    /**
     * 头像url
     */
    private String avatarUrl;

    /**
     * 背景url
     */
    private String backgroundUrl;


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
