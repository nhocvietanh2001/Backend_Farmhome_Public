package com.ute.farmhome.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ute.farmhome.entity.Fruit;
import com.ute.farmhome.repository.FruitRepository;
import com.ute.farmhome.service.FruitService;

@Service
public class FruitServiceImplement implements FruitService {

	@Autowired
	FruitRepository fruitRepository;
	@Override
	public List<Fruit> getAllFruit() {
		
		return fruitRepository.findAllFruit();
	}
	
}
