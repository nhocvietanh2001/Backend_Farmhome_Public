package com.ute.farmhome.mapper.implement;

import com.ute.farmhome.dto.HistoryDTO;
import com.ute.farmhome.dto.OrderDTO;
import com.ute.farmhome.entity.History;
import com.ute.farmhome.mapper.FruitMapper;
import com.ute.farmhome.mapper.HistoryMapper;
import com.ute.farmhome.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class HistoryMapperImplement implements HistoryMapper {
    @Autowired
    FruitMapper fruitMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public HistoryDTO map(History entity) {
        HistoryDTO orderDTO = new HistoryDTO();
        orderDTO.setId(entity.getId());
        orderDTO.setFruit(fruitMapper.map(entity.getFruit()));
        orderDTO.setFarmer(userMapper.mapToShow(entity.getFarmer()));
        orderDTO.setMerchant(userMapper.mapToShow(entity.getMerchant()));
        orderDTO.setPrice(entity.getPrice());
        orderDTO.setDate(String.valueOf(entity.getDate()));
        orderDTO.setTransport(entity.getTransport());
        return orderDTO;
    }

    @Override
    public History map(HistoryDTO historyDTO) {
        return null;
    }
}
