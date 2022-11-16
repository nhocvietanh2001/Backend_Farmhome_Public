package com.ute.farmhome.controller.common;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ute.farmhome.dto.JwtResponse;
import com.ute.farmhome.dto.LoginRequest;
import com.ute.farmhome.entity.Role;
import com.ute.farmhome.entity.User;
import com.ute.farmhome.service.UserService;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.FORBIDDEN;


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
        String accessToken = this.jwtUtils.generateJwtAccessToken(authentication);
        String refreshToken = this.jwtUtils.generateJwtRefreshToken(authentication);
        org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        User userDto = userService.findByUsername(user.getUsername());
        long id = userDto.getId();
        List<String> roles = user.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
        return new ResponseEntity<JwtResponse>(new JwtResponse(accessToken, refreshToken, user.getUsername(), roles, userDto.getAvatar(), id), HttpStatus.CREATED);
    }
    @GetMapping(value = "/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws StreamWriteException, DatabindException, IOException
    {
        String authorizationHeader = request.getHeader("AUTHORIZATION");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("ute".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                User user = userService.findByUsername(username);
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 3 * 60 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> maps = new HashMap<String, String>();
                maps.put("access_token", access_token);
                maps.put("refesh_token", refresh_token);
                response.setContentType("APPLICATION_JSON_VALUE");
                new ObjectMapper().writeValue(response.getOutputStream(), maps);
            } catch (Exception e) {
                response.setHeader("error", e.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> maps = new HashMap<String, String>();
                String error = "Token lỗi";
                maps.put("message", error);
                maps.put("message system", e.getMessage());
                maps.put("httpCode", String.valueOf(FORBIDDEN.value()));
                response.setContentType("APPLICATION_JSON_VALUE");//SET BODY JSON, NEU KHONG SET MAC DINH SE LA TEXT
                new ObjectMapper().writeValue(response.getOutputStream(), maps);//GHI RA BODY
            }
        } else {
            throw new RuntimeException("Refesh token is missing");
        }

    }


}
