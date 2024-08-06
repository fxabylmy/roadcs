package com.jingjin.model.user.dto.user;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class UploadBackgroundDTO implements Serializable {
    /**
     * 头像
     */
    private MultipartFile background;
    /**
     * 串行版本uid
     */
    private static final long serialVersionUID = 1L;
}
