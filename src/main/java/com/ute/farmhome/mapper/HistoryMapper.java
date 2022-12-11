package com.ute.farmhome.mapper;

import com.ute.farmhome.dto.HistoryDTO;
import com.ute.farmhome.entity.History;
import com.ute.farmhome.entity.Order;

public interface HistoryMapper {
    HistoryDTO map(History entity);
    History map(HistoryDTO historyDTO);
    History mapFromOrder(Order order);
}
