package com.ute.farmhome.service.implement;

import com.google.api.client.util.DateTime;
import com.ute.farmhome.dto.HistoryDTO;
import com.ute.farmhome.dto.OrderDTO;
import com.ute.farmhome.dto.PaginationDTO;
import com.ute.farmhome.entity.Fruit;
import com.ute.farmhome.entity.Order;
import com.ute.farmhome.entity.StatusProduct;
import com.ute.farmhome.entity.User;
import com.ute.farmhome.exception.ResourceNotFound;
import com.ute.farmhome.mapper.OrderMapper;
import com.ute.farmhome.repository.OrderRepository;
import com.ute.farmhome.service.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImplement implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    FruitService fruitService;
    @Autowired
    HistoryService historyService;
    @Autowired
    UserService userService;
    @Autowired
    StatusService statusService;
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
        StatusProduct statusPending = statusService.getPendingStatusProduct();
        order.setStatus(statusPending);
        return orderMapper.map(orderRepository.save(order));
    }

    @Override
    public PaginationDTO getByMerchantId(int id, int no, int limit) {
        Pageable pageable = PageRequest.of(no, limit);
        List<?> listOrder = orderRepository.findByMerchantId(id, pageable).stream().map(item -> orderMapper.map(item)).toList();
        Page<Order> page = orderRepository.findByMerchantId(id, pageable);
        return new PaginationDTO(listOrder, page.isFirst(), page.isLast(), page.getTotalPages(), page.getTotalElements(), page.getSize(), page.getNumber());
    }

    @Override
    public PaginationDTO getByFarmerId(int id, int no, int limit) {
        Pageable pageable = PageRequest.of(no, limit);
        List<?> listOrder = orderRepository.findByFarmerId(id, pageable).stream().map(item -> orderMapper.map(item)).toList();
        Page<Order> page = orderRepository.findByFarmerId(id, pageable);
        return new PaginationDTO(listOrder, page.isFirst(), page.isLast(), page.getTotalPages(), page.getTotalElements(), page.getSize(), page.getNumber());
    }

    @Override
    public OrderDTO changePrice(OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderDTO.getId()).orElseThrow(() -> new ResourceNotFound("Order", "id", String.valueOf(orderDTO.getId())));
        order.setPrice(orderDTO.getPrice());
        StatusProduct statusDealing = statusService.getDealingStatusProduct();
        order.setStatus(statusDealing);
        return orderMapper.map(orderRepository.save(order));
    }

    @Override
    public OrderDTO resendOrder(OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderDTO.getId()).orElseThrow(() -> new ResourceNotFound("Order", "id", String.valueOf(orderDTO.getId())));
        order.setPrice(orderDTO.getPrice());
        StatusProduct statusPending = statusService.getPendingStatusProduct();
        order.setStatus(statusPending);
        return orderMapper.map(orderRepository.save(order));
    }

    @Override
    public HistoryDTO acceptOrder(OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderDTO.getId())
                .orElseThrow(() -> new ResourceNotFound("Order", "id", String.valueOf(orderDTO.getId())));
        return historyService.createHistoryFromOrder(order);
    }
}
