package com.jingjin.model.adminWebsite.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeleteFavoritesDTO implements Serializable {
    /**
     * 站内网站id
     */
    private Integer adminWebsiteId;

    /**
     * 串行版本uid
     */
    private static final long serialVersionUID = 1L;
}
