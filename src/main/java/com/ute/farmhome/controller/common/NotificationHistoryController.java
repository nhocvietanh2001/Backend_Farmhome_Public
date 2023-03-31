package com.ute.farmhome.controller.common;

import com.ute.farmhome.service.NotificationHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notificationHistory")
public class NotificationHistoryController {
    @Autowired
    NotificationHistoryService notificationHistoryService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getNotificationHistory(@PathVariable int id) {
        return ResponseEntity.ok(notificationHistoryService.getById(id));
    }
    @GetMapping("/user/{uid}")
    public ResponseEntity<?> getAllNotificationHistory(@PathVariable int uid) {
        return ResponseEntity.ok(notificationHistoryService.getByUserId(uid));
    }
}
