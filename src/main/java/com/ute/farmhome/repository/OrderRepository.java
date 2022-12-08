package com.ute.farmhome.repository;

import org.springframework.data.domain.Page;
import com.ute.farmhome.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.merchant.id = :id")
    Page<Order> findByMerchantId(int id, Pageable pageable);
    @Query("SELECT o FROM Order o WHERE o.farmer.id = :id")
    Page<Order> findByFarmerId(int id, Pageable pageable);
}
