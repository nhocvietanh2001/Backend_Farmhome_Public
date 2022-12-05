package com.ute.farmhome.controller.common;

import com.ute.farmhome.dto.OrderDTO;
import com.ute.farmhome.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("/create")
    public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO dto) {
        return new ResponseEntity(orderService.createOrder(dto), HttpStatus.CREATED);
    }
}
