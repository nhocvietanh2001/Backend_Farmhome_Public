package com.ute.farmhome.repository;

import com.ute.farmhome.dto.UserShowDTO;
import com.ute.farmhome.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
