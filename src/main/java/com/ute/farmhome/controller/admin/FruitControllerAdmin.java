package com.ute.farmhome.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ute.farmhome.service.FruitService;

@RestController
@RequestMapping("admin/fruit")
public class FruitControllerAdmin {
	@Autowired
	FruitService fruitService;
	@GetMapping()
	public ResponseEntity<?> getAllFruit() {
		return ResponseEntity.ok(fruitService.getAllFruit());
	}
}
