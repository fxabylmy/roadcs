package com.jingjin.thirdpartywebsiteservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jingjin.model.thirdPartyWebsite.po.ThirdPartyWebsite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ThirdPartyWebsiteMapper extends BaseMapper<ThirdPartyWebsite> {
    @Select("SELECT * " +
            "FROM `third_party_website` " +
            "WHERE MATCH (name) AGAINST (#{name}) " +
            "AND `is_delete` = 0 " +
            "AND `STATUS` = 1;")
    List<ThirdPartyWebsite> search(@Param("name")String name);
}
