package com.jingjin.model.thirdPartyWebsite.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class ThirdPartyWebsitePageVO implements Serializable {
    /**
     * 页面索引
     */
    private Integer pageIndex;

    /**
     * 页面大小
     */
    private Integer pageSize;

    /**
     * 总数
     */
    private Long total;

    private List<ThirdPartyWebsiteSimpleVO> thirdPartyWebsiteSimpleVOList;

    public static final long serialVersionUID = 1L;
}
