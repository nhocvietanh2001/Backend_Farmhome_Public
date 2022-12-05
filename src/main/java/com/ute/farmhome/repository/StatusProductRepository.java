package com.ute.farmhome.repository;

import com.ute.farmhome.entity.StatusProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusProductRepository extends CrudRepository<StatusProduct, Integer> {
}
