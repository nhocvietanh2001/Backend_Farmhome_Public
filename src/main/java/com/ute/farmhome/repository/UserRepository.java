package com.ute.farmhome.repository;

import com.ute.farmhome.dto.UserShowDTO;
import com.ute.farmhome.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.username = :username")
    Optional<User> findByUsername(String username);
    @Query("SELECT u FROM User u")
    Page<User> findAllUserPaging(Pageable pageable);
    @Query("SELECT case WHEN count(u)> 0 then true else false end FROM User u WHERE u.username = :username")
    boolean existByUsername(String username);
}
