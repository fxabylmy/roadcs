package com.jingjin.model.user.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UploadPresupposeBackgroundDTO implements Serializable {
    /**
     * 预设背景图片Url
     */
    private String backgroundUrl;
    /**
     * 串行版本uid
     */
    private static final long serialVersionUID = 1L;
}
