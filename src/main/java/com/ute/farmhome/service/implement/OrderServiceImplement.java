package com.ute.farmhome.service.implement;

import com.ute.farmhome.dto.OrderDTO;
import com.ute.farmhome.entity.Fruit;
import com.ute.farmhome.entity.Order;
import com.ute.farmhome.entity.User;
import com.ute.farmhome.mapper.OrderMapper;
import com.ute.farmhome.repository.OrderRepository;
import com.ute.farmhome.service.FruitService;
import com.ute.farmhome.service.OrderService;
import com.ute.farmhome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImplement implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    FruitService fruitService;
    @Autowired
    UserService userService;
    @Autowired
    OrderMapper orderMapper;
    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.map(orderDTO);
        Fruit fruit = fruitService.findFruitById(orderDTO.getFruit().getId());
        order.setFruit(fruit);
        User farmer = userService.findById(orderDTO.getFarmer().getId());
        order.setFarmer(farmer);
        User merchant = userService.findById(orderDTO.getMerchant().getId());
        order.setMerchant(merchant);
        return orderMapper.map(orderRepository.save(order));
    }
}
