package com.ute.farmhome.service.implement;

import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.util.List;

import com.ute.farmhome.dto.PaginationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ute.farmhome.entity.Fruit;
import com.ute.farmhome.repository.FruitRepository;
import com.ute.farmhome.service.FruitService;

@Service
public class FruitServiceImplement implements FruitService {
	@Autowired
	FruitRepository fruitRepository;
	@Override
	public PaginationDTO getAllFruit(int no, int limit) {
		PageRequest pageRequest = PageRequest.of(no, limit);
		List<Fruit> fruits = fruitRepository.findAllFruit(pageRequest).stream().toList();
		Page<Fruit> page = fruitRepository.findAllFruit(pageRequest);
		return new PaginationDTO(fruits, page.isFirst(), page.isLast(), page.getTotalPages(), page.getTotalElements(), page.getSize(), page.getNumber());
	}
	@Override
	public Fruit getFruitById(int id) {
		return fruitRepository.findById(id).get();
	}
}
