package com.ute.farmhome.controller.admin;

import com.ute.farmhome.dto.FruitDTO;
import com.ute.farmhome.dto.UserCreateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ute.farmhome.service.FruitService;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin/fruit")
public class FruitControllerAdmin {
	@Autowired
	private FruitService fruitService;
	@PostMapping(value = "create", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> createFruit(@RequestPart String fruit, @RequestPart List<MultipartFile> images) {
		FruitDTO fruitDTO = fruitService.readJson(fruit, images);
		return new ResponseEntity(fruitService.createFruit(fruitDTO), HttpStatus.CREATED);
	}

	@PutMapping(value = "update", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> updateFruit(@RequestPart String fruit, @RequestPart(required = false) List<MultipartFile> images) {
		FruitDTO fruitDTO = fruitService.readJson(fruit, images);
		return ResponseEntity.ok(fruitService.updateFruit(fruitDTO));
	}

	@GetMapping("/suggestPrice/{fruitName}")
	public ResponseEntity<?> getSuggestPrice(@PathVariable String fruitName) throws Exception {
		return ResponseEntity.ok(fruitService.crawlData(fruitName));
	}
}
