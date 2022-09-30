package com.ute.farmhome.repository;

import com.ute.farmhome.entity.StatusUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusUserRepository extends CrudRepository<StatusUser, Integer> {
}
