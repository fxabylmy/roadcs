package com.jingjin.model.thirdPartyWebsite.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddThirdPartyWebsiteDTO implements Serializable {
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
     * 类型
     */
    private Integer type;

    /**
     * 知识类别
     */
    private Integer category;

    /**
     * 重要性级别
     */
    private Integer importanceLevel;

    /**
     * 副标题
     */
    private String subtitle;

    /**
     * 建议
     */
    private String recommendation;

    private static final long serialVersionUID = 1L;
}
