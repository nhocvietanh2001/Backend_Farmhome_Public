package com.ute.farmhome.service;

import com.ute.farmhome.dto.PaginationDTO;
import com.ute.farmhome.dto.UserCreateDTO;
import com.ute.farmhome.dto.UserShowDTO;
import com.ute.farmhome.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    UserCreateDTO readJson(String user, MultipartFile avatar);
    User createUser(UserCreateDTO userCreateDTO);
    User findByUsername(String username);
    PaginationDTO getAllUserPaging(int no, int number);

}
