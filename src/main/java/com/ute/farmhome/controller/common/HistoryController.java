package com.ute.farmhome.controller.common;

import com.ute.farmhome.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    HistoryService historyService;
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getByUserId(@PathVariable int id, @RequestParam HashMap<String, String> hashMap) {
        int no = Integer.parseInt(hashMap.getOrDefault("no", "0"));
        int limit = Integer.parseInt(hashMap.getOrDefault("limit", "20"));

        return ResponseEntity.ok(historyService.getByUserId(id, no, limit));
    }
}
