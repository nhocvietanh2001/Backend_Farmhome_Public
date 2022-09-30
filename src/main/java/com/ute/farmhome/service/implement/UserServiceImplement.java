package com.ute.farmhome.service.implement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ute.farmhome.dto.UserCreateDTO;
import com.ute.farmhome.dto.UserShowDTO;
import com.ute.farmhome.entity.Role;
import com.ute.farmhome.entity.StatusUser;
import com.ute.farmhome.entity.User;
import com.ute.farmhome.repository.RoleRepository;
import com.ute.farmhome.repository.StatusUserRepository;
import com.ute.farmhome.repository.UserRepository;
import com.ute.farmhome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserServiceImplement implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private StatusUserRepository statusUserRepository;
    @Override
    public UserCreateDTO readJson(String user) {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            userCreateDTO = objectMapper.readValue(user, UserCreateDTO.class);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        StatusUser statusUser = statusUserRepository.findById(userCreateDTO.getStatus().getId()).get();
        Role role = roleRepository.findById(userCreateDTO.getRole().getId()).get();
        userCreateDTO.setStatus(statusUser);
        userCreateDTO.setRole(role);
        return userCreateDTO;
    }
    @Override
    public User createUser(UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setId(userCreateDTO.getId());
        user.setUsername(userCreateDTO.getUsername());
        user.setPassword(userCreateDTO.getPassword());
        user.setAvatar(userCreateDTO.getAvatar());
        user.setFirstName(userCreateDTO.getFirstName());
        user.setLastName(userCreateDTO.getLastName());
        user.setEmail(userCreateDTO.getEmail());
        user.setCreateDate(LocalDate.now());
        user.setStatus(userCreateDTO.getStatus());
        user.setRole(userCreateDTO.getRole());
//        user.setStatus(statusUserRepository.findById(userCreateDTO.getStatus().getId()).get());
//        user.setRole(roleRepository.findById(userCreateDTO.getRole().getId()).get());
        return userRepository.save(user);
    }
}
