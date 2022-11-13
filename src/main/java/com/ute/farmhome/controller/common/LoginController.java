package com.ute.farmhome.controller.common;


import java.util.List;
import java.util.stream.Collectors;

import com.ute.farmhome.dto.JwtResponse;
import com.ute.farmhome.dto.LoginRequest;
import com.ute.farmhome.entity.User;
import com.ute.farmhome.service.UserService;
import com.ute.farmhome.service.implement.UserDetailsImpl;
import com.ute.farmhome.utility.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserService userService;

    public final Logger logger = LoggerFactory.getLogger("info");

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.jwtUtils.generateJwtToken(authentication);
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        User userDto = userService.findByUsername(user.getUsername());
        long id = userDto.getId();
        List<String> roles = user.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
        return new ResponseEntity<JwtResponse>(new JwtResponse(jwt, user.getUsername(), roles, userDto.getAvatar(), id), HttpStatus.CREATED);
    }

}
