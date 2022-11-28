package com.ute.farmhome.service.implement;

import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ute.farmhome.dto.*;
import com.ute.farmhome.exception.ResourceNotFound;
import com.ute.farmhome.mapper.FruitMapper;
import com.ute.farmhome.utility.UpdateFile;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ute.farmhome.entity.Fruit;
import com.ute.farmhome.repository.FruitRepository;
import com.ute.farmhome.service.FruitService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FruitServiceImplement implements FruitService {
	@Autowired
	private FruitRepository fruitRepository;
	@Autowired
	private UpdateFile updateFile;
	@Autowired
	private FruitMapper fruitMapper;
	@Override
	public PaginationDTO getAllFruit(int no, int limit) {
		PageRequest pageRequest = PageRequest.of(no, limit);
		List<FruitShowDTO> fruits = fruitRepository.findAllFruit(pageRequest).stream().map(item -> fruitMapper.mapToShow(item)).toList();
		Page<Fruit> page = fruitRepository.findAllFruit(pageRequest);
		return new PaginationDTO(fruits, page.isFirst(), page.isLast(), page.getTotalPages(), page.getTotalElements(), page.getSize(), page.getNumber());
	}
	@Override
	public FruitShowDTO getFruitById(int id) {
		return fruitMapper.mapToShow(fruitRepository.findById(id).get());
	}
	@Override
	public FruitDTO readJson(String fruit, MultipartFile image) {
		FruitDTO fruitDTO = new FruitDTO();
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			fruitDTO = objectMapper.readValue(fruit, FruitDTO.class);
			if(image != null) {
				fruitDTO.setImageFile(image);
			}
		} catch (JsonMappingException e) {
			throw new RuntimeException(e);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		return fruitDTO;
	}
	@Override
	public FruitShowDTO createFruit(FruitDTO fruitDTO) {
		if (fruitDTO.getImageFile() != null) {
			FileUpload fileUpload = new FileUpload();
			fileUpload.setFile(fruitDTO.getImageFile());
			updateFile.update(fileUpload);
			fruitDTO.setImage(fileUpload.getOutput());
		}
		Fruit fruit = fruitMapper.map(fruitDTO);
		return fruitMapper.mapToShow(fruitRepository.save(fruit));
	}

	@Override
	public PaginationDTO searchFruit(String name, int no, int limit) {
		Pageable pageable = PageRequest.of(no, limit);
		List<FruitShowDTO> listFruit = fruitRepository.searchByName(name, pageable).stream().map(item -> fruitMapper.mapToShow(item)).toList();
		Page<Fruit> page = fruitRepository.searchByName(name, pageable);
		return new PaginationDTO(listFruit, page.isFirst(), page.isLast(), page.getTotalPages(), page.getTotalElements(), page.getSize(), page.getNumber());
	}

	@Override
	public FruitShowDTO updateFruit(FruitDTO fruitDTO) {
		Fruit fruit = fruitRepository.findById(fruitDTO.getId())
				.orElseThrow(() -> new ResourceNotFound("fruit", "id", String.valueOf(fruitDTO.getId())));

		fruit.setName(fruitDTO.getName());
		fruit.setWeight(fruitDTO.getWeight());
		fruit.setUnit(fruitDTO.getUnit());
		fruit.setDate(LocalDate.parse(fruitDTO.getDate()));
		fruit.setSeason(fruitDTO.getSeason());
		if (fruitDTO.getImageFile() != null) {
			FileUpload fileUpload = new FileUpload();
			fileUpload.setFile(fruitDTO.getImageFile());
			updateFile.update(fileUpload);
			fruitDTO.setImage(fileUpload.getOutput());
			fruit.setImage(fruitDTO.getImage());
		}
		return fruitMapper.mapToShow(fruitRepository.save(fruit));
	}

	@Override
	public PaginationDTO getFruitByUserId(int id, int no, int limit) {
		Pageable pageable = PageRequest.of(no, limit);
		List<?> listFruit = fruitRepository.getFruitByUserId(id, pageable).stream().map(item -> fruitMapper.mapToShow(item)).toList();
		Page<?> page = fruitRepository.getFruitByUserId(id, pageable);
		return new PaginationDTO(listFruit, page.isFirst(), page.isLast(), page.getTotalPages(), page.getTotalElements(), page.getSize(), page.getNumber());
	}


}
