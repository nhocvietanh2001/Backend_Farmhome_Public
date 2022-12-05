package com.ute.farmhome.repository;

import com.ute.farmhome.entity.Ward;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends CrudRepository<Ward, Integer> {
}
