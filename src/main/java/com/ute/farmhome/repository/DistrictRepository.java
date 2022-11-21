package com.ute.farmhome.repository;

import com.ute.farmhome.entity.District;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends CrudRepository<District, Integer> {
}
