package com.ute.farmhome.repository;

import org.springframework.data.domain.Page;
import com.ute.farmhome.entity.History;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends CrudRepository<History, Integer> {
    @Query("SELECT h FROM History h WHERE h.farmer.id = :id or h.merchant.id = :id")
    Page<History> findByFarmerOrMerchantId(int id, Pageable pageable);
}
