package com.ute.farmhome.service;

import com.ute.farmhome.entity.User;
import com.ute.farmhome.entity.UserLogin;

public interface UserLoginService {
    UserLogin save(User user, String deviceId);
}
