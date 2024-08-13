package com.jingjin.model.thirdPartyWebsite.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddFavoritesDTO implements Serializable {
    /**
     * 站内网站id
     */
    private Integer thirdPartyWebsiteId;

    /**
     * 串行版本uid
     */
    private static final long serialVersionUID = 1L;
}
