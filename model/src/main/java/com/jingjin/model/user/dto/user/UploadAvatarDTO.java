package com.jingjin.model.user.dto.user;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * 上传头像请求体
 *
 * @author fxab
 * @date 2024/08/06
 */
@Data
public class UploadAvatarDTO implements Serializable {

    /**
     * 头像
     */
    private MultipartFile avatar;
    /**
     * 串行版本uid
     */
    private static final long serialVersionUID = 1L;
}
