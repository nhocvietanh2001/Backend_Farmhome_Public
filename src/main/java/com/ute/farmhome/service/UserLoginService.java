package com.ute.farmhome.service;

import com.ute.farmhome.entity.User;
import com.ute.farmhome.entity.UserLogin;

import java.util.Optional;

public interface UserLoginService {
    UserLogin save(User user, String deviceId);
    Optional<UserLogin> findByUserId(int id);
}
