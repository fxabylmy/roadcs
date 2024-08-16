package com.jingjin.model.user.dto.user;

import lombok.Data;

/**
 * ClassName: UploadPasswordDTO
 * Description:
 *
 * @Author zjm
 */
@Data
public class UploadPasswordDTO {
    /**
     * 串行版本uid
     */
    private static final long serialVersionUID = 1L;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户设置的新密码
     */
    private String newPassword;

}
