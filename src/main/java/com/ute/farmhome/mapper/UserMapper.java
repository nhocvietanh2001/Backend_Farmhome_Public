package com.ute.farmhome.mapper;

import com.ute.farmhome.dto.UserCreateDTO;
import com.ute.farmhome.dto.UserShowDTO;
import com.ute.farmhome.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserCreateDTO map(User user);
    User map(UserCreateDTO dto);
    UserShowDTO mapToShow(User user);
}
