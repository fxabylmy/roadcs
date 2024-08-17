package com.jingjin.userservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jingjin.model.user.po.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * ClassName: RolePermissionMapper
 * Description:
 *
 * @Author zjm
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    @Select("<script>" +
            "SELECT perms FROM permission WHERE id IN " +
            "<foreach item='item' index='index' collection='roleIds' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</script>")
    List<String> findPermsByPermissionIds(@Param("roleIds") List<String> roleIds);
}
