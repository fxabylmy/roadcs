package com.jingjin.model.userInteraction.vo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class UserMemoPageVO implements Serializable {
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

    private List<UserMemoVO> userMemoVOList;


    public static final long serialVersionUID = 1L;
}
