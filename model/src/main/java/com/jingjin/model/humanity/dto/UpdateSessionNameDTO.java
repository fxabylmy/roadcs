package com.jingjin.model.humanity.dto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSessionNameDTO implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 会话名
     */
    private String name;


    /**
     * 串行版本uid
     */
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
