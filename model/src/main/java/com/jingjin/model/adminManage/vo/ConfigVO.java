package com.jingjin.model.adminManage.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfigVO implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 变量名
     */
    private String variable;

    /**
     * 变量值
     */
    private String value;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;


    /**
     * 串行版本uid
     */
    private static final long serialVersionUID = 1L;
}
