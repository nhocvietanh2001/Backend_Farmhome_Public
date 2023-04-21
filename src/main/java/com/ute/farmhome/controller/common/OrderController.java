package com.ute.farmhome.controller.common;

import com.ute.farmhome.dto.OrderDTO;
import com.ute.farmhome.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PostMapping("/create")
    public ResponseEntity<OrderDTO> create(@RequestBody OrderDTO dto) {
        return new ResponseEntity(orderService.createOrder(dto), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(orderService.getById(id));
    }
    @GetMapping("/merchant/{id}")
    public ResponseEntity<?> getByMerchant(@PathVariable int id, @RequestParam(required = false) HashMap<String, String> hashMap) {
        int no = Integer.parseInt(hashMap.getOrDefault("no", "0"));
        int limit = Integer.parseInt(hashMap.getOrDefault("limit", "20"));

        return ResponseEntity.ok(orderService.getByMerchantId(id, no, limit));
    }
    @GetMapping("/farmer/{id}")
    public ResponseEntity<?> getByFarmer(@PathVariable int id, @RequestParam(required = false) HashMap<String, String> hashMap) {
        int no = Integer.parseInt(hashMap.getOrDefault("no", "0"));
        int limit = Integer.parseInt(hashMap.getOrDefault("limit", "20"));

        return ResponseEntity.ok(orderService.getByFarmerId(id, no, limit));
    }
    @PutMapping("/suggest")
    public ResponseEntity<?> suggestPrice(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(orderService.changePrice(orderDTO));
    }
    @PutMapping("/resend")
    public ResponseEntity<?> resendOrder(@RequestBody OrderDTO orderDTO) {
        return ResponseEntity.ok(orderService.resendOrder(orderDTO));
    }
    @PostMapping("/accept/{id}")
    public ResponseEntity<?> acceptOrder(@PathVariable int id) {
        return new ResponseEntity<>(orderService.acceptOrder(id), HttpStatus.CREATED);
    }
    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<?> cancelOrder(@PathVariable int id, @RequestParam(required = false) HashMap<String, String> hashMap) {
        String reason = hashMap.getOrDefault("reason", null);
        HashMap<String, String> response = new HashMap<>();
        response.put("message", "Deleted order");
        response.put("http code", "200");
        orderService.deleteOrder(id, reason);
        return ResponseEntity.ok(response);
    }
}
