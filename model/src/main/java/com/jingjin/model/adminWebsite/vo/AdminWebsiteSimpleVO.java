package com.jingjin.model.adminWebsite.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 管理网站simple vo
 *
 * @author fxab
 * @date 2024/08/08
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminWebsiteSimpleVO implements Serializable {
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
     * 串行版本uid
     */
    private static final long serialVersionUID = 1L;
}
