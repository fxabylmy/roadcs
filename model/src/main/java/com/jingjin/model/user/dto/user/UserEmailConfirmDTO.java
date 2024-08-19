package com.jingjin.model.user.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * ClassName: UserEmailConfirmDTO
 * Description:
 *
 * @Author zjm
 */
@Data
@Schema(description = "用户邮箱验证码验证")
public class UserEmailConfirmDTO {
    private static final long serialVersionUID = 1L;

    /**
     * 用户邮箱
     */
    @Schema(description = "用户邮箱")
    private String email;

    /**
     * 验证码
     */
    @Schema(description = "用户邮箱验证码")
    private String emailCode;
}
