package com.jingjin.model.user.dto.user;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class UploadCustomBackgroundDTO implements Serializable {
    /**
     * 自定义背景图片
     */
    private MultipartFile background;
    /**
     * 串行版本uid
     */
    private static final long serialVersionUID = 1L;
}
