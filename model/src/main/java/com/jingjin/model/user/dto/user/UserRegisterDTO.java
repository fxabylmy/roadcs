package com.jingjin.model.user.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册dto
 *
 * @author fxab
 * @date 2024/08/05
 */
@Data
public class UserRegisterDTO implements Serializable {

    /**
     * 电子邮件
     */
    @NotNull(message = "缺少注册用户邮箱")
    private String email;

    /**
     * 密码
     */
    @NotNull(message = "缺少注册用户密码")
    private String password;

    /**
     * 验证码
     */
    @NotNull(message = "缺少注册用户邮箱验证码")
    private String verificationCode;

    /**
     * 串行版本uid
     */
    private static final long serialVersionUID = 1L;
}
