package com.jingjin.model.user.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName: Permission
 * Description:
 *
 * @Author zjm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Permission implements Serializable {
    /**
     * 串行版本uid
     */
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO) //指定id的增长类型自增
    private Integer id;

    /**
     * 权限名称
     */
    private String name;

    /**
     * 权限路由
     */
    private String perms;

    /**
     * 权限的状态
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除(0不删除,1删除)
     */
    @TableLogic
    private Integer isDelete;

}
