package com.ute.farmhome.service;

import com.ute.farmhome.dto.*;
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
    Boolean changePassword(String username, UserChangePassDTO userChangePassDTO);
    PaginationDTO getAllMerchant(int no, int limit);
    PaginationDTO getAllFarmer(int no, int limit);
    PaginationDTO searchMerchant(int no, int limit, String username);
    PaginationDTO searchFarmer(int no, int limit, String username);
    MerchantDetailDTO getMerchantDetail(int id, int no, int limit);
    FarmerDetailDTO getFarmerDetail(int id, int no, int limit);
}
