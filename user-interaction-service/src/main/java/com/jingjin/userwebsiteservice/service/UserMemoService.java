package com.jingjin.userwebsiteservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jingjin.common.result.PageResponse;
import com.jingjin.model.userInteraction.po.UserMemo;
import com.jingjin.model.userInteraction.vo.UserMemoVO;

public interface UserMemoService extends IService<UserMemo> {

    PageResponse<UserMemoVO> getPageById(int pageIndex, int pageSize, String userId);
}
