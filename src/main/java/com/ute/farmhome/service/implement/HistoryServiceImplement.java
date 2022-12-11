package com.ute.farmhome.service.implement;

import com.ute.farmhome.dto.HistoryDTO;
import com.ute.farmhome.entity.History;
import com.ute.farmhome.entity.Order;
import com.ute.farmhome.mapper.HistoryMapper;
import com.ute.farmhome.repository.HistoryRepository;
import com.ute.farmhome.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImplement implements HistoryService {
    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    HistoryMapper historyMapper;
    @Override
    public HistoryDTO createHistoryFromOrder(Order order) {
        History history = historyMapper.mapFromOrder(order);
        return historyMapper.map(historyRepository.save(history));
    }
}
