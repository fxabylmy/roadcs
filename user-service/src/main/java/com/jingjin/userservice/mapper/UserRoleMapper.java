package com.jingjin.userservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jingjin.model.user.po.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName: UserRoleMapper
 * Description:
 *
 * @Author zjm
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    @Select("SELECT role_id FROM user_role WHERE user_id = #{userId}")
    List<String> findRoleIdsByUserId(@Param("userId") String userId);
}
