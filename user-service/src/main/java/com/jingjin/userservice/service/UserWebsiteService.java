package com.jingjin.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jingjin.common.result.PageResponse;
import com.jingjin.model.userInteraction.dto.AddUserWebsiteDTO;
import com.jingjin.model.userInteraction.dto.UpdateUserWebsiteDTO;
import com.jingjin.model.userInteraction.po.UserWebsite;
import com.jingjin.model.userInteraction.vo.UserWebsiteVO;

public interface UserWebsiteService extends IService<UserWebsite> {
    Boolean addUserWebsite(String userId, AddUserWebsiteDTO addUserWebsiteDTO);

    Boolean updateUserWebsite(UpdateUserWebsiteDTO updateUserWebsiteDTO);

    PageResponse<UserWebsiteVO> getPageByUserId(String userId, int pageIndex, int pageSize);
}
