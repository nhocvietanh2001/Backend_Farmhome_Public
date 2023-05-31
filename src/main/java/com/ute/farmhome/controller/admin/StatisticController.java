package com.ute.farmhome.controller.admin;

import com.ute.farmhome.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;

@RestController
@RequestMapping("admin/statistic")
public class StatisticController {
    @Autowired
    StatisticService statisticService;
    @GetMapping("pie-chart")
    public ResponseEntity<?> statisticPieChart(@RequestParam HashMap<String, String> hashMap) {
        LocalDate startDate = LocalDate.parse(hashMap.getOrDefault("startDate", "2022-01-01"));
        LocalDate endDate = LocalDate.parse(hashMap.getOrDefault("endDate", LocalDate.now().toString()));
        return ResponseEntity.ok(statisticService.statisticPieChart(startDate, endDate));
    }
    @GetMapping("bar-chart")
    public ResponseEntity<?> statisticBarChart(@RequestParam HashMap<String, String> hashMap) {
        LocalDate startDate = LocalDate.parse(hashMap.getOrDefault("startDate", "2022-01-01"));
        LocalDate endDate = LocalDate.parse(hashMap.getOrDefault("endDate", LocalDate.now().toString()));
        return ResponseEntity.ok(statisticService.statisticBarChart(startDate, endDate));
    }

    @GetMapping("merchant")
    public ResponseEntity<?> statisticMerchant() {
        return ResponseEntity.ok(statisticService.statisticMerchant());
    }

    @GetMapping("farmer")
    public ResponseEntity<?> statisticFarmer() {
        return ResponseEntity.ok(statisticService.statisticFarmer());
    }

    @GetMapping("user")
    public ResponseEntity<?> statisticUser() {
        return ResponseEntity.ok(statisticService.statisticUser());
    }

    @GetMapping("date/{day}")
    public ResponseEntity<?> statisticDate(@PathVariable int day){
        return ResponseEntity.ok(statisticService.statisticDate(day));
    }

    @GetMapping("stats")
    public ResponseEntity<?> getStats() {
        return ResponseEntity.ok(statisticService.getStats());
    }
}
