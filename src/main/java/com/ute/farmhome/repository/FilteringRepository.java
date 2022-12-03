package com.ute.farmhome.repository;

import com.ute.farmhome.entity.Fruit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FilteringRepository {
    Page<Fruit> filterFruit(String name, Float amount, List<String> season, Boolean popular, String order, Pageable pageable);
}
