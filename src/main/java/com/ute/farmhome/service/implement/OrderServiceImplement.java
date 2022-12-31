package com.ute.farmhome.service.implement;

import com.ute.farmhome.dto.HistoryDTO;
import com.ute.farmhome.dto.OrderDTO;
import com.ute.farmhome.dto.PaginationDTO;
import com.ute.farmhome.entity.*;
import com.ute.farmhome.exception.ExceedAmount;
import com.ute.farmhome.exception.ResourceNotFound;
import com.ute.farmhome.mapper.OrderMapper;
import com.ute.farmhome.repository.OrderRepository;
import com.ute.farmhome.service.*;
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
    LocationService locationService;
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
        if (orderDTO.getDeliveryLocation() != null) {
            Location location = locationService.findById(orderDTO.getDeliveryLocation().getId());
            order.setDeliveryLocation(location);
        }
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
        order.setDealPrice(orderDTO.getDealPrice());
        order.setDealAmount(orderDTO.getDealAmount());
        StatusProduct statusDealing = statusService.getDealingStatusProduct();
        order.setStatus(statusDealing);
        return orderMapper.map(orderRepository.save(order));
    }

    @Override
    public OrderDTO resendOrder(OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderDTO.getId()).orElseThrow(() -> new ResourceNotFound("Order", "id", String.valueOf(orderDTO.getId())));
        if(order.getDealPrice() != null) {
            order.setPrice(order.getDealPrice());
            order.setDealPrice(null);
        }
        if(order.getDealAmount() != null) {
            order.setAmount(order.getDealAmount());
            order.setDealAmount(null);
        }
        StatusProduct statusPending = statusService.getPendingStatusProduct();
        order.setStatus(statusPending);
        return orderMapper.map(orderRepository.save(order));
    }

    @Override
    public HistoryDTO acceptOrder(int id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Order", "id", String.valueOf(id)));
        Fruit fruit = fruitService.findFruitById(order.getFruit().getId());
        if (fruit.getRemainingWeight() == fruit.getWeight()) {
            fruit.setRemainingWeight(fruit.getWeight() - order.getAmount());
            fruitService.save(fruit);
        } else {
            if (fruit.getRemainingWeight() < order.getAmount()) {
                throw new ExceedAmount();
            } else {
                fruit.setRemainingWeight(fruit.getRemainingWeight() - order.getAmount());
                fruitService.save(fruit);
            }
        }
        HistoryDTO historyDTOSaved = historyService.createHistoryFromOrder(order);
        if (historyDTOSaved != null) {
            orderRepository.deleteById(order.getId());
        }
        return historyDTOSaved;
    }
    private void calculateRemaining(int id) {
        Fruit fruit = fruitService.findFruitById(id);
        List<Order> orders = getByFruitId(id);
        float totalWeight = 0;
        for (Order order : orders) {
            totalWeight += order.getAmount();
        }
        fruit.setRemainingWeight(fruit.getWeight() - totalWeight);
        fruitService.save(fruit);
    }
    @Override
    public OrderDTO getById(int id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Order", "id", String.valueOf(id)));
        return orderMapper.map(order);
    }

    @Override
    public void deleteOrder(int id) {
        try {
            orderRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getByFruitId(int id) {
        return orderRepository.findByFruitId(id);
    }
}
