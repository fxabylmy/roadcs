package com.jingjin.model.thirdPartyWebsite.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThirdPartyWebsiteDetailVO implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 网站名
     */
    private String name;

    /**
     * logoBase64字符串
     */
    private String logoBase64;

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
     * 推荐理由
     */
    private String recommendation;


    /**
     * 串行版本uid
     */
    private static final long serialVersionUID = 1L;
}
