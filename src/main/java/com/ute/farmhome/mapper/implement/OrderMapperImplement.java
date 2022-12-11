package com.ute.farmhome.mapper.implement;

import com.ute.farmhome.dto.OrderDTO;
import com.ute.farmhome.entity.Order;
import com.ute.farmhome.entity.StatusProduct;
import com.ute.farmhome.exception.ResourceNotFound;
import com.ute.farmhome.mapper.FruitMapper;
import com.ute.farmhome.mapper.OrderMapper;
import com.ute.farmhome.mapper.UserMapper;
import com.ute.farmhome.repository.StatusProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class OrderMapperImplement implements OrderMapper {
    @Autowired
    StatusProductRepository statusProductRepository;
    @Autowired
    FruitMapper fruitMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public OrderDTO map(Order entity) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(entity.getId());
        orderDTO.setFruit(fruitMapper.map(entity.getFruit()));
        orderDTO.setFarmer(userMapper.mapToShow(entity.getFarmer()));
        orderDTO.setMerchant(userMapper.mapToShow(entity.getMerchant()));
        orderDTO.setPrice(entity.getPrice());
        orderDTO.setAmount(entity.getAmount());
        orderDTO.setDate(String.valueOf(entity.getDate()));
        orderDTO.setTransport(entity.getTransport());
        orderDTO.setStatus(entity.getStatus());
        return orderDTO;
    }

    @Override
    public Order map(OrderDTO orderDTO) {
        Order entity = new Order();
        entity.setId(orderDTO.getId());
        entity.setPrice(orderDTO.getPrice());
        entity.setAmount(orderDTO.getAmount());
        entity.setDate(LocalDate.now());
        entity.setTransport(orderDTO.getTransport());
        return entity;
    }
}
