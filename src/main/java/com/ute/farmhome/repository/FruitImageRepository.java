package com.ute.farmhome.repository;

import com.ute.farmhome.entity.FruitImage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitImageRepository extends CrudRepository<FruitImage, Integer> {
}
