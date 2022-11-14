package com.ute.farmhome.controller.admin;

import com.ute.farmhome.dto.UserCreateDTO;
import com.ute.farmhome.entity.Role;
import com.ute.farmhome.entity.StatusUser;
import com.ute.farmhome.repository.RoleRepository;
import com.ute.farmhome.repository.StatusUserRepository;
import com.ute.farmhome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@RestController
@RequestMapping("/admin/user")
public class UserControllerAdmin {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private StatusUserRepository statusUserRepository;
    @PostMapping(value = "create", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createUser(@RequestPart("user") String user) {
        UserCreateDTO userCreateDTO = userService.readJson(user);
        return ResponseEntity.ok(userService.createUser(userCreateDTO));
    }
    @GetMapping()
    public ResponseEntity<?> getAllUserPaging(@RequestParam HashMap<String, String> hashMap) {
        int no = Integer.parseInt(hashMap.getOrDefault("no", "0"));
        int number = Integer.parseInt(hashMap.getOrDefault("number", "5"));
        return ResponseEntity.ok(userService.getAllUserPaging(no, number));
    }
    @PreAuthorize("hasAuthority('ROLE_FARMER')")
    @GetMapping(value = "farmer")
    public ResponseEntity<?> decentralized() {
        return ResponseEntity.ok().body("this is for farmer only");
    }
    @PreAuthorize("hasRole('MERCHANT')")
    @GetMapping(value = "merchant")
    public ResponseEntity<?> decentralizedS() {
        return ResponseEntity.ok().body("this is for merchant only");
    }
    @PostConstruct
    public void addData() {
        Role mer = roleRepository.save(new Role(0, "MERCHANT"));
        Role farm = roleRepository.save(new Role(0, "FARMER"));
        Role admin = roleRepository.save(new Role(0, "ADMIN"));

        Collection<Role> roles = new ArrayList<>();
        roles.add(admin);
        roles.add(farm);
        roles.add(mer);

        StatusUser active = statusUserRepository.save(new StatusUser(0, "Active"));
        StatusUser inactive = statusUserRepository.save(new StatusUser(0, "Inactive"));

        userService.createUser(new UserCreateDTO(0,
                "vietanh",
                "Password123",
                "Password123",
                null,
                "Viet",
                "Anh",
                "vietanh@gmail.com",
                null,
                active,
                roles));
    }
}
