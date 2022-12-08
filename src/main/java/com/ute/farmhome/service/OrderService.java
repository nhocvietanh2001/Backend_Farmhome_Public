package com.ute.farmhome.service;

import com.ute.farmhome.dto.OrderDTO;
import com.ute.farmhome.dto.PaginationDTO;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
    PaginationDTO getByMerchantId(int id, int no, int limit);
    PaginationDTO getByFarmerId(int id, int no, int limit);
    OrderDTO changePrice(OrderDTO orderDTO);
    OrderDTO resendOrder(OrderDTO orderDTO);
}
