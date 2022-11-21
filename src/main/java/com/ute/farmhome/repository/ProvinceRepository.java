package com.ute.farmhome.repository;

import com.ute.farmhome.entity.Province;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends CrudRepository<Province, Integer> {
}
