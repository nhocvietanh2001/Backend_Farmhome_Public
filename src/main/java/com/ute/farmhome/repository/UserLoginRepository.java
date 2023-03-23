package com.ute.farmhome.repository;

import com.ute.farmhome.entity.User;
import com.ute.farmhome.entity.UserLogin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserLoginRepository extends CrudRepository<UserLogin, Integer> {
    @Query("SELECT u FROM UserLogin u WHERE u.deviceId = :deviceId")
    Optional<UserLogin> findByDeviceId(String deviceId);
    @Query("SELECT u FROM UserLogin u WHERE u.user.id = :userId")
    Optional<UserLogin> findByUserId(int userId);
}
