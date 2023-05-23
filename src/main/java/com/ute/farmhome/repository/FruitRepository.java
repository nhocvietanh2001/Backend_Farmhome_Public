package com.ute.farmhome.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ute.farmhome.entity.Fruit;

@Repository
public interface FruitRepository extends CrudRepository<Fruit, Integer>, FilteringRepository {
	@Query("SELECT f FROM Fruit f WHERE f.farmer IS NOT NULL")
	Page<Fruit> findAllFruit(PageRequest pageRequest);
	@Query("SELECT f FROM Fruit f WHERE f.name like %:name% AND f.farmer IS NOT NULL")
	Page<Fruit> searchByName(String name, Pageable pageable);
	@Query("SELECT f FROM Fruit f WHERE f.farmer.id = :id")
	Page<Fruit> getFruitByUserId(@Param("id") int id, Pageable pageable);
	@Query("SELECT f FROM Fruit f WHERE f.category.category = :category AND f.farmer IS NOT NULL")
	Page<Fruit> getFruitByCategory(String category, Pageable pageable);
}
