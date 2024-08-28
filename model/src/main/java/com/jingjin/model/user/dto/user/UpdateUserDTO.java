package com.jingjin.model.user.dto.user;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UpdateUserDTO implements Serializable {

    /**
     * 昵称
     */
    private String name;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 签名
     */
    private String signature;


    /**
     * 串行版本uid
     */
    private static final long serialVersionUID = 1L;
}
