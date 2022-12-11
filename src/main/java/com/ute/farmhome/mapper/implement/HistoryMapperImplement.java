package com.ute.farmhome.mapper.implement;

import com.ute.farmhome.dto.HistoryDTO;
import com.ute.farmhome.dto.OrderDTO;
import com.ute.farmhome.entity.History;
import com.ute.farmhome.entity.Order;
import com.ute.farmhome.entity.StatusProduct;
import com.ute.farmhome.exception.ResourceNotFound;
import com.ute.farmhome.mapper.FruitMapper;
import com.ute.farmhome.mapper.HistoryMapper;
import com.ute.farmhome.mapper.UserMapper;
import com.ute.farmhome.repository.StatusProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class HistoryMapperImplement implements HistoryMapper {
    @Autowired
    StatusProductRepository statusProductRepository;
    @Autowired
    FruitMapper fruitMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public HistoryDTO map(History entity) {
        HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setId(entity.getId());
        historyDTO.setFruit(fruitMapper.mapToShow(entity.getFruit()));
        historyDTO.setFarmer(userMapper.mapToShow(entity.getFarmer()));
        historyDTO.setMerchant(userMapper.mapToShow(entity.getMerchant()));
        historyDTO.setPrice(entity.getPrice());
        historyDTO.setDate(String.valueOf(entity.getDate()));
        historyDTO.setTransport(entity.getTransport());
        return historyDTO;
    }

    @Override
    public History map(HistoryDTO historyDTO) {
        History entity = new History();
        entity.setId(historyDTO.getId());
        entity.setPrice(historyDTO.getPrice());
        entity.setDate(LocalDate.parse(historyDTO.getDate()));
        entity.setTransport(historyDTO.getTransport());
        return entity;
    }

    @Override
    public History mapFromOrder(Order order) {
        History history = new History();
        history.setFruit(order.getFruit());
        history.setFarmer(order.getFarmer());
        history.setMerchant(order.getMerchant());
        history.setPrice(order.getPrice());
        history.setAmount(order.getAmount());
        history.setDate(LocalDate.now());
        history.setTransport(order.getTransport());
        return history;
    }
}
