package com.ute.farmhome.service;

import com.ute.farmhome.dto.PaginationDTO;
import com.ute.farmhome.dto.UserCreateDTO;
import com.ute.farmhome.dto.UserShowDTO;
import com.ute.farmhome.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    UserCreateDTO readJson(String user, MultipartFile avatar);
    UserShowDTO createUser(UserCreateDTO userCreateDTO);
    User findByUsername(String username);
    PaginationDTO getAllUserPaging(int no, int number);
    UserShowDTO getByUsername(String username);
    UserShowDTO updateUser(UserCreateDTO userCreateDTO);
    UserShowDTO getById(int id);
    User findById(int id);
}
