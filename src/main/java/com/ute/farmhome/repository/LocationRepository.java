package com.ute.farmhome.repository;

import com.ute.farmhome.entity.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Integer> {
    @Query("SELECT l FROM Location l WHERE l in (SELECT u.location FROM User u WHERE u.id = :id)")
    Location findByUserId(int id);
}
