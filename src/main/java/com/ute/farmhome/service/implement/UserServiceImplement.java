package com.ute.farmhome.service.implement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ute.farmhome.dto.PaginationDTO;
import com.ute.farmhome.dto.UserCreateDTO;
import com.ute.farmhome.dto.UserShowDTO;
import com.ute.farmhome.entity.Role;
import com.ute.farmhome.entity.StatusUser;
import com.ute.farmhome.entity.User;
import com.ute.farmhome.exception.ResourceNotFound;
import com.ute.farmhome.exception.ValidationException;
import com.ute.farmhome.repository.RoleRepository;
import com.ute.farmhome.repository.StatusUserRepository;
import com.ute.farmhome.repository.UserRepository;
import com.ute.farmhome.service.UserService;
import com.ute.farmhome.utility.Validation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImplement implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private StatusUserRepository statusUserRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private Validation validation;
    public final static Logger log = LoggerFactory.getLogger("info");
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found in the database"));
        log.info("username {} found.", username);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
    @Override
    public UserCreateDTO readJson(String user, MultipartFile avatar) {
        UserCreateDTO userCreateDTO = new UserCreateDTO();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            userCreateDTO = objectMapper.readValue(user, UserCreateDTO.class);
            if(avatar != null) {
                userCreateDTO.setAvatar(avatar);
            }
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return userCreateDTO;
    }
    @Override
    public User createUser(UserCreateDTO userCreateDTO) {
        User user = new User();
        if (!validateData(userCreateDTO)) {
            return null;
        }
        user.setId(userCreateDTO.getId());
        user.setUsername(userCreateDTO.getUsername());
        if (userCreateDTO.getPassword().equals(userCreateDTO.getConfirmPassword())) {
            user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        }
        user.setFirstName(userCreateDTO.getFirstName());
        user.setLastName(userCreateDTO.getLastName());
        user.setEmail(userCreateDTO.getEmail());
        user.setCreateDate(LocalDate.now());
        StatusUser statusUser = statusUserRepository.findById(userCreateDTO.getStatus().getId())
                .orElseThrow(() -> new ResourceNotFound("StatusUser", "id", String.valueOf(userCreateDTO.getStatus().getId())));
        user.setStatus(statusUser);
        userCreateDTO.getRoles().forEach(role -> {
            Role findRole = roleRepository.findById(role.getId())
                    .orElseThrow(() -> new ResourceNotFound("Role", "id", String.valueOf(role.getId())));
            user.getRoles().add(findRole);
        });
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    private Boolean validateData(UserCreateDTO userCreateDTO) {
        HashMap<String, String> mapError = new HashMap<>();
        if (userCreateDTO.getUsername().length() < 6) {
            mapError.put("username length", "Username must 6 characters or above");
        }
        if (!userCreateDTO.getPassword().equals(userCreateDTO.getConfirmPassword())) {
            mapError.put("confirm password", "Password and confirm password does not match");
        }
        if (!validation.validatePassword(userCreateDTO.getPassword())) {
            mapError.put("password", "Password must be 6 digits, have at least 1 capital, and have at least 1 number");
        }
        if (userRepository.existByUsername(userCreateDTO.getUsername())) {
            mapError.put("username", "Username existed");
        }
        if (userRepository.existByUsername(userCreateDTO.getEmail())) {
            mapError.put("email", "Email existed");
        }
        if (mapError.size() > 0) {
            throw new ValidationException(mapError);
        }
        return true;
    }
    @Override
    public PaginationDTO getAllUserPaging(int no, int number) {
        Pageable pageable = PageRequest.of(no, number);
        List<User> userList = userRepository.findAllUserPaging(pageable).stream().toList();
        Page<User> userPage = userRepository.findAllUserPaging(pageable);
        return new PaginationDTO(userList, userPage.isFirst(), userPage.isLast(), userPage.getTotalPages(), userPage.getTotalElements(), userPage.getSize(), userPage.getNumber());
    }

}
