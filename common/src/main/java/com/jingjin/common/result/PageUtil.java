package com.jingjin.common.result;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;


/**
 * 分页工具类
 *
 * @author fxab
 * @date 2024/08/14
 */
public class PageUtil {
    /**
     * 成功
     *
     * @param pageCurrent 当前页面
     * @param pageSize    页面大小
     * @param pageCount   页面计数
     * @param total       合计
     * @param date        日期
     * @return {@link PageResponse}<{@link T}>
     */
    public static <T> PageResponse<T> setPage(long pageCurrent, long pageSize,
                                              long pageCount, long total, List<T> date) {
        return new PageResponse<>(pageCurrent,pageSize,pageCount,total,date
        );
    }

    /**
     * 成功
     *
     * @param page 第页
     * @param date 日期
     * @return {@link PageResponse}<{@link T}>
     */
    public static <T> PageResponse<T> setPage(IPage page, List<T> date) {
        return new PageResponse<>(page.getCurrent(),
                page.getSize(),
                page.getPages(),
                page.getTotal(),
                date
        );
    }

    public static <T> PageResponse<T> setPage(IPage page) {
        return new PageResponse<>(page.getCurrent(),
                page.getSize(),
                page.getPages(),
                page.getTotal(),
                page.getRecords()
        );
    }

    public static <T> PageResponse<T> isNull(List<T> date) {
        return new PageResponse<>(0,0,0,0,date
        );
    }


}
