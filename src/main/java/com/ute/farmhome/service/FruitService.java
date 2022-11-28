package com.ute.farmhome.service;

import java.util.List;

import com.ute.farmhome.dto.FruitDTO;
import com.ute.farmhome.dto.FruitShowDTO;
import com.ute.farmhome.dto.PaginationDTO;
import com.ute.farmhome.entity.Fruit;
import org.springframework.web.multipart.MultipartFile;

public interface FruitService {
	PaginationDTO getAllFruit(int no, int limit);
	FruitShowDTO getFruitById(int id);
	FruitDTO readJson(String fruit, MultipartFile image);
	FruitShowDTO createFruit(FruitDTO fruitDTO);
	PaginationDTO searchFruit(String name, int no, int limit);
	FruitShowDTO updateFruit(FruitDTO fruitDTO);
}
