package com.jingjin.thirdpartywebsiteservice.util.converter;

import com.jingjin.model.thirdPartyWebsite.po.ThirdPartyWebsite;
import com.jingjin.model.thirdPartyWebsite.vo.ThirdPartyWebsiteDetailVO;
import com.jingjin.model.thirdPartyWebsite.vo.ThirdPartyWebsiteSimpleVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ThirdPartyWebsiteConverter {
    /**
     * 实例
     */
    ThirdPartyWebsiteConverter INSTANCE = Mappers.getMapper(ThirdPartyWebsiteConverter.class);

    ThirdPartyWebsiteSimpleVO toWebsiteSimpleVO(ThirdPartyWebsite thirdPartyWebsite);

    ThirdPartyWebsiteDetailVO toWebsiteDetailVO(ThirdPartyWebsite thirdPartyWebsite);
}
