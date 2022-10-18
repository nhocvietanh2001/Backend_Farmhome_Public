package com.ute.farmhome.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ute.farmhome.entity.Fruit;

@Repository
public interface FruitRepository extends CrudRepository<Fruit, Integer>{
	@Query("SELECT f FROM Fruit f")
	Page<Fruit> findAllFruit(PageRequest pageRequest);
}
