package com.jingjin.model.adminWebsite.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class AdminWebsitePageVO implements Serializable {
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

    private List<AdminWebsiteSimpleVO> adminWebsiteSimpleVOList;

    public static final long serialVersionUID = 1L;
}
