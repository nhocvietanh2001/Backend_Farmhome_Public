package com.ute.farmhome.service;

import com.ute.farmhome.dto.HistoryDTO;
import com.ute.farmhome.dto.PaginationDTO;
import com.ute.farmhome.entity.History;
import com.ute.farmhome.entity.Order;

public interface HistoryService {
    HistoryDTO createHistoryFromOrder(Order order);
    PaginationDTO getByUserId(int id, int no, int limit);
}
