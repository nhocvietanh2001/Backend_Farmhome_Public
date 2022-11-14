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
import com.ute.farmhome.repository.RoleRepository;
import com.ute.farmhome.repository.StatusUserRepository;
import com.ute.farmhome.repository.UserRepository;
import com.ute.farmhome.service.UserService;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
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

        return userCreateDTO;
    }
    @Override
    public User createUser(UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setId(userCreateDTO.getId());
        user.setUsername(userCreateDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));
        user.setAvatar(userCreateDTO.getAvatar());
        user.setFirstName(userCreateDTO.getFirstName());
        user.setLastName(userCreateDTO.getLastName());
        user.setEmail(userCreateDTO.getEmail());
        user.setCreateDate(LocalDate.now());
        StatusUser statusUser = statusUserRepository.findById(userCreateDTO.getStatus().getId())
                .orElseThrow(() -> new ResourceNotFound("StatusUser", "id", String.valueOf(userCreateDTO.getStatus().getId())));
        user.setStatus(statusUser);
        Collection<Role> roles = new ArrayList<>();
        userCreateDTO.getRoles().forEach(role -> {
            Role findRole = roleRepository.findById(role.getId()).orElseThrow(() -> new ResourceNotFound("Role", "id", String.valueOf(role.getId())));
            user.getRoles().add(findRole);
        });
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    @Override
    public PaginationDTO getAllUserPaging(int no, int number) {
        Pageable pageable = PageRequest.of(no, number);
        List<User> userList = userRepository.findAllUserPaging(pageable).stream().toList();
        Page<User> userPage = userRepository.findAllUserPaging(pageable);
        return new PaginationDTO(userList, userPage.isFirst(), userPage.isLast(), userPage.getTotalPages(), userPage.getTotalElements(), userPage.getSize(), userPage.getNumber());
    }

}
