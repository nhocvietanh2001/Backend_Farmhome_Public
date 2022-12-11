package com.ute.farmhome.repository;

import com.ute.farmhome.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface NewsRepository extends CrudRepository<News, Integer> {
    @Query("SELECT n FROM News n")
    Page<News> findAllPaging(Pageable pageable);
}
