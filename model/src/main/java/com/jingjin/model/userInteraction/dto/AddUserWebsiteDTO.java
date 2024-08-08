package com.jingjin.model.userInteraction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddUserWebsiteDTO implements Serializable {
    /**
     * 网站名
     */
    private String name;

    /**
     * 徽标
     */
    private MultipartFile logo;

    /**
     * 网站url
     */
    private String websiteUrl;


    /**
     * 副标题
     */
    private String subtitle;


    /**
     * 串行版本uid
     */
    private static final long serialVersionUID = 1L;
}
