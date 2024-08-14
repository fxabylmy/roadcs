package com.jingjin.common.result;


import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询响应实体类
 *
 * @author fxab
 * @date 2024/04/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponse<T> implements Serializable {

    /**
     * 页码
     */
    private long pageCurrent;

    /**
     * 页大小
     */
    private long pageSize;

    /**
     * 总页数
     */
    private long pageCount;

    /**
     * 总数据量
     */
    private long total;

    /**
     * 数据
     */
    private List<T> data;


    private static final long serialVersionUID = 1L;


}

