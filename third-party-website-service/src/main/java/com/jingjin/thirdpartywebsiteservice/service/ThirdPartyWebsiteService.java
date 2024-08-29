package com.jingjin.thirdpartywebsiteservice.service;

import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jingjin.model.thirdPartyWebsite.dto.AddThirdPartyWebsiteDTO;
import com.jingjin.model.thirdPartyWebsite.dto.UpdateThirdPartyWebsiteDTO;
import com.jingjin.model.thirdPartyWebsite.po.ThirdPartyWebsite;
import com.jingjin.model.thirdPartyWebsite.vo.ThirdPartyWebsiteDetailVO;
import com.jingjin.model.thirdPartyWebsite.vo.ThirdPartyWebsitePageVO;
import com.jingjin.model.thirdPartyWebsite.vo.ThirdPartyWebsiteSimpleVO;

import java.util.List;

public interface ThirdPartyWebsiteService extends IService<ThirdPartyWebsite> {

    List<ThirdPartyWebsiteSimpleVO> getAll();

    ThirdPartyWebsiteDetailVO getDetailById(Integer ThirdPartyWebsiteId);

    Boolean addThirdPartyWebsite(AddThirdPartyWebsiteDTO addThirdPartyWebsiteDTO);

    Boolean updateThirdPartyWebsite(UpdateThirdPartyWebsiteDTO updateThirdPartyWebsiteDTO);

    List<ThirdPartyWebsiteSimpleVO> getByIds(List<Integer> ids);

    @Cached(name="ThirdPartyWebsiteService.getByType", expire = 36000)
    List<ThirdPartyWebsiteSimpleVO> getByType(Integer type);

    List<ThirdPartyWebsiteSimpleVO> search(String name);
}
