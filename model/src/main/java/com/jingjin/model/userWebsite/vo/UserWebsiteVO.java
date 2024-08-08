package com.jingjin.model.userWebsite.vo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 站内网站vo
 *
 * @author fxab
 * @date 2024/08/08
 */
@Builder
@Data
public class UserWebsiteVO implements Serializable {

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
     * 副标题
     */
    private String subtitle;


    /**
     * 串行版本uid
     */
    private static final long serialVersionUID = 1L;
}
