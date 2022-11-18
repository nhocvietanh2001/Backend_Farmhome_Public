package com.ute.farmhome.controller.admin;

import com.ute.farmhome.dto.FruitDTO;
import com.ute.farmhome.dto.UserCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ute.farmhome.service.FruitService;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admin/fruit")
public class FruitControllerAdmin {
	@Autowired
	FruitService fruitService;
	@GetMapping()
	public ResponseEntity<?> getAllFruit(@RequestParam(required = false) Map<String, String> params) {
		int no = Integer.parseInt(params.getOrDefault("no", "0"));
		int limit = Integer.parseInt(params.getOrDefault("limit", "5"));
		return ResponseEntity.ok(fruitService.getAllFruit(no, limit));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getFruitByID(@PathVariable int id) {
		return ResponseEntity.ok(fruitService.getFruitById(id));
	}

	@PostMapping(value = "create", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> createFruit(@RequestPart String fruit, @RequestPart MultipartFile image) {
		FruitDTO fruitDTO = fruitService.readJson(fruit, image);
		return ResponseEntity.ok(fruitService.createFruit(fruitDTO));
	}

	@GetMapping("/search")
	public ResponseEntity<?> searchFruit(@RequestParam String name, @RequestParam HashMap<String, String> hashMap) {
		int no = Integer.parseInt(hashMap.getOrDefault("no", "0"));
		int limit = Integer.parseInt(hashMap.getOrDefault("limit", "5"));

		return ResponseEntity.ok(fruitService.searchFruit(name, no, limit));
	}
}
