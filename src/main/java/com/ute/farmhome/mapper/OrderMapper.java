package com.ute.farmhome.mapper;

import com.ute.farmhome.dto.OrderDTO;
import com.ute.farmhome.entity.Order;

public interface OrderMapper {
    OrderDTO map(Order entity);
    Order map(OrderDTO orderDTO);
}
