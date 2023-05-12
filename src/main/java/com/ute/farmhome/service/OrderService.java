package com.ute.farmhome.service;

import com.ute.farmhome.dto.HistoryDTO;
import com.ute.farmhome.dto.OrderDTO;
import com.ute.farmhome.dto.PaginationDTO;
import com.ute.farmhome.entity.Order;

import java.util.List;

public interface OrderService {
    OrderDTO createOrder(OrderDTO orderDTO);
    PaginationDTO getByMerchantId(int id, int no, int limit);
    PaginationDTO getByFarmerId(int id, int no, int limit);
    OrderDTO changePrice(OrderDTO orderDTO);
    OrderDTO resendOrder(OrderDTO orderDTO);
    HistoryDTO acceptOrder(int id);
    OrderDTO getOrderDtoById(int id);
    void deleteOrder(int id, String reason);
    List<Order> getByFruitId(int id);
    List<OrderDTO> getListByMerchantUserId(int id, int no, int limit);
}
