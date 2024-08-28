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
     * 昵称
     */
    private String name;

    /**
     * 邮箱地址
     */
    private String email;


    /**
     * 性别
     */
    private Integer sex;

    /**
     * 签名
     */
    private String signature;

    /**
     * 头像url
     */
    private String thumbAvatarUrl;


    /**
     * 串行版本uid
     */
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
