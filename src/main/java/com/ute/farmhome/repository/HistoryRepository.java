package com.ute.farmhome.repository;

import com.ute.farmhome.entity.History;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends CrudRepository<History, Integer> {
}
