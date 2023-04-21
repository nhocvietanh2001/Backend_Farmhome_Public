package com.ute.farmhome.service;

import java.net.MalformedURLException;
import java.util.List;

import com.ute.farmhome.dto.FruitDTO;
import com.ute.farmhome.dto.FruitShowDTO;
import com.ute.farmhome.dto.PaginationDTO;
import com.ute.farmhome.dto.TableFruitDTO;
import com.ute.farmhome.entity.Fruit;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface FruitService {
	PaginationDTO getAllFruit(int no, int limit);
	List<TableFruitDTO> getAllFruitByTable(int no, int limit);
	FruitShowDTO getFruitById(int id) throws Exception;
	FruitDTO readJson(String fruit, List<MultipartFile> image);
	FruitShowDTO createFruit(FruitDTO fruitDTO);
	PaginationDTO searchFruit(String name, int no, int limit);
	FruitShowDTO updateFruit(FruitDTO fruitDTO);
	PaginationDTO getFruitByUserId(int id, int no, int limit);
	PaginationDTO filterPaging(String name, Float amount, List<String> seasonList, Boolean popular, String order, int no, int limit);
	Fruit findFruitById(int id);
	void deleteById(int id);
	void save(Fruit fruit);
	String crawlData(String fruitName) throws Exception;
}
