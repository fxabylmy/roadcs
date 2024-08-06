package com.jingjin.model.user.vo;

import com.baomidou.mybatisplus.annotation.*;
import jakarta.validation.constraints.NotNull;
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
public class UserDetailVO implements Serializable {

    /**
     * id
     */
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
     * 电话
     */
    private String phone;

    /**
     * 微信
     */
    private String wechat;


    /**
     * 串行版本uid
     */
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
