package com.jingjin.model.userInteraction.dto;

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
public class OpinionResponseDTO implements Serializable {
    /**
     * id
     */
    private Integer id;



    /**
     * 回复内容
     */
    private String responseContent;

    /**
     * 串行版本uid
     */
    private static final long serialVersionUID = 1L;
}
