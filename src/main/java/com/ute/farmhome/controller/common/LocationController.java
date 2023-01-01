package com.ute.farmhome.controller.common;

import com.ute.farmhome.dto.LocationDTO;
import com.ute.farmhome.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    LocationService locationService;
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getByUserId(@PathVariable int id) {
        return ResponseEntity.ok(locationService.findByUserId(id));
    }
}
