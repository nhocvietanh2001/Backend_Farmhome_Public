package com.ute.farmhome.service;

import com.ute.farmhome.dto.UserCreateDTO;
import com.ute.farmhome.dto.UserShowDTO;
import com.ute.farmhome.entity.User;

public interface UserService {
    UserCreateDTO readJson(String user);
    User createUser(UserCreateDTO userCreateDTO);
}
