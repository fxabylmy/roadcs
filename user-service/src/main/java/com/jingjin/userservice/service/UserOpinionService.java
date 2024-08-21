package com.jingjin.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jingjin.common.result.PageResponse;
import com.jingjin.model.userInteraction.po.UserOpinion;
import com.jingjin.model.userInteraction.vo.UserOpinionVO;

public interface UserOpinionService extends IService<UserOpinion> {
    PageResponse<UserOpinionVO> getPageById(String userId, int pageIndex, int pageSize);
}
