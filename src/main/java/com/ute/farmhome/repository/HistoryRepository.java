package com.ute.farmhome.repository;

import org.springframework.data.domain.Page;
import com.ute.farmhome.entity.History;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface HistoryRepository extends CrudRepository<History, Integer> {
    @Query("SELECT h FROM History h WHERE h.farmer.id = :id or h.merchant.id = :id ORDER BY h.id DESC")
    Page<History> findByFarmerOrMerchantId(int id, Pageable pageable);
    @Query("SELECT h.fruit.name as name, sum(h.price * h.amount) as total FROM History h WHERE h.date >= :startDate and h.date <= :endDate GROUP BY h.fruit")
    List<Object[]> statisticFruitByDate(LocalDate startDate, LocalDate endDate);
    @Query("SELECT h.date as date, sum(h.price * h.amount) as total FROM History h WHERE h.date >= :startDate and h.date <= :endDate GROUP BY h.date ORDER BY h.date")
    List<Object[]> statisticByDate(LocalDate startDate, LocalDate endDate);

}
