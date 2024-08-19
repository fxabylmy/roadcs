package com.jingjin.userservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jingjin.model.user.po.UserRole;
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
public interface RolePermissionMapper extends BaseMapper<UserRole> {
    @Select("<script>" +
            "SELECT permission " +
            "FROM role_permission " +
            "WHERE role_id IN " +
            "<foreach collection='roleIds' item='roleId' open='(' separator=',' close=')'>" +
            "#{roleId}" +
            "</foreach>" +
            "</script>")
    List<String> findPermissionsByRoleIds(@Param("roleIds") List<String> roleIds);
}
