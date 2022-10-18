package com.ute.farmhome.service;

import java.util.List;

import com.ute.farmhome.dto.PaginationDTO;
import com.ute.farmhome.entity.Fruit;

public interface FruitService {
	PaginationDTO getAllFruit(int no, int limit);
	Fruit getFruitById(int id);
}
