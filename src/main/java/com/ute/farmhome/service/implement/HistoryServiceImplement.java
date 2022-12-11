package com.ute.farmhome.service.implement;

import org.springframework.data.domain.Page;
import com.ute.farmhome.dto.HistoryDTO;
import com.ute.farmhome.dto.PaginationDTO;
import com.ute.farmhome.entity.History;
import com.ute.farmhome.entity.Order;
import com.ute.farmhome.mapper.HistoryMapper;
import com.ute.farmhome.repository.HistoryRepository;
import com.ute.farmhome.repository.OrderRepository;
import com.ute.farmhome.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public PaginationDTO getByUserId(int id, int no, int limit) {
        Pageable pageable = PageRequest.of(no, limit);
        List<?> listHistory = historyRepository.findByFarmerOrMerchantId(id, pageable).stream().map(item -> historyMapper.map(item)).toList();
        Page<?> page = historyRepository.findByFarmerOrMerchantId(id, pageable);
        return new PaginationDTO(listHistory, page.isFirst(), page.isLast(), page.getTotalPages(), page.getTotalElements(), page.getSize(), page.getNumber());
    }

}
