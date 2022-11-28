package com.ute.farmhome.controller.common;

import com.ute.farmhome.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("fruit")
public class FruitController {
    @Autowired
    FruitService fruitService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getFruitByID(@PathVariable int id) {
        return ResponseEntity.ok(fruitService.getFruitById(id));
    }
    @GetMapping()
    public ResponseEntity<?> getAllFruit(@RequestParam(required = false) Map<String, String> params) {
        int no = Integer.parseInt(params.getOrDefault("no", "0"));
        int limit = Integer.parseInt(params.getOrDefault("limit", "5"));
        return ResponseEntity.ok(fruitService.getAllFruit(no, limit));
    }
    @GetMapping("/search")
    public ResponseEntity<?> searchFruit(@RequestParam String name, @RequestParam(required = false) HashMap<String, String> hashMap) {
        int no = Integer.parseInt(hashMap.getOrDefault("no", "0"));
        int limit = Integer.parseInt(hashMap.getOrDefault("limit", "5"));

        return ResponseEntity.ok(fruitService.searchFruit(name, no, limit));
    }
    @GetMapping("/farmer/{id}")
    public ResponseEntity<?> getFruitByUserId(@PathVariable int id, @RequestParam(required = false) HashMap<String, String> hashMap) {
        int no = Integer.parseInt(hashMap.getOrDefault("no", "0"));
        int limit = Integer.parseInt(hashMap.getOrDefault("limit", "5"));

        return ResponseEntity.ok(fruitService.getFruitByUserId(id, no, limit));
    }
}
