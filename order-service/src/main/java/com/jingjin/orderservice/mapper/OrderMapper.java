package com.jingjin.orderservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.jingjin.model.order.po.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
