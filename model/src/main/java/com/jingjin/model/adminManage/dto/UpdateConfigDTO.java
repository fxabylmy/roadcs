package com.jingjin.model.adminManage.dto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateConfigDTO implements Serializable {

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
     * 串行版本uid
     */
    private static final long serialVersionUID = 1L;
}
