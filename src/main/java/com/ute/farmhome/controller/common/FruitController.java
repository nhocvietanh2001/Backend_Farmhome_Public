package com.ute.farmhome.controller.common;

import com.ute.farmhome.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("fruit")
public class FruitController {
    @Autowired
    FruitService fruitService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getFruitByID(@PathVariable int id) throws Exception {
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

    @GetMapping("/filter")
    public ResponseEntity<?> filter(@RequestParam(required = false) HashMap<String, String> hashMap,
                                    @RequestParam(required = false) String name,
                                    @RequestParam(required = false) Float amount,
                                    @RequestParam(required = false) List<String> seasonList,
                                    @RequestParam(required = false) Boolean popular,
                                    @RequestParam(required = false, defaultValue = "last") String order) {
        int no = Integer.parseInt(hashMap.getOrDefault("no", "0"));
        int limit = Integer.parseInt(hashMap.getOrDefault("limit", "5"));

        return ResponseEntity.ok(fruitService.filterPaging(name, amount, seasonList, popular, order, no, limit));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        fruitService.deleteById(id);
        HashMap message = new HashMap<>();
        message.put("message", "Fruit deleted");
        return ResponseEntity.ok(message);
    }
}
