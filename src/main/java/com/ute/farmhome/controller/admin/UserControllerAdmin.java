package com.ute.farmhome.controller.admin;

import com.ute.farmhome.dto.UserCreateDTO;
import com.ute.farmhome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/user")
public class UserControllerAdmin {
    @Autowired
    private UserService userService;

    @PostMapping(value = "create", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createUser(@RequestPart("user") String user) {
        UserCreateDTO userCreateDTO = userService.readJson(user);
        return ResponseEntity.ok(userService.createUser(userCreateDTO));
    }
}
