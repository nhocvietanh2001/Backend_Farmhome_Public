package com.ute.farmhome.controller.admin;

import com.ute.farmhome.dto.UserChangePassDTO;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@RestController
@RequestMapping("/admin/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserControllerAdmin {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private StatusUserRepository statusUserRepository;
    @PostMapping(value = "create", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createUser(@RequestPart("user") String user, @RequestPart(value = "avatar", required = false) MultipartFile avatar) {
        UserCreateDTO userCreateDTO = userService.readJson(user, avatar);
        return ResponseEntity.ok(userService.createUser(userCreateDTO));
    }
    @GetMapping()
    public ResponseEntity<?> getAllUserPaging(@RequestParam HashMap<String, String> hashMap) {
        int no = Integer.parseInt(hashMap.getOrDefault("no", "0"));
        int limit = Integer.parseInt(hashMap.getOrDefault("limit", "5"));
        return ResponseEntity.ok(userService.getAllUserPaging(no, limit));
    }
    @PreAuthorize("hasAuthority('ROLE_FARMER')")
    @GetMapping(value = "farmer")
    public ResponseEntity<?> decentralized() {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "This is for farmer only");
        return ResponseEntity.ok().body(map);
    }
    @PreAuthorize("hasRole('MERCHANT')")
    @GetMapping(value = "merchant")
    public ResponseEntity<?> decentralizedS() {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", "This is for merchant only");
        return ResponseEntity.ok().body(map);
    }
    @PutMapping(value = "update", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updateUser(@RequestPart String user, @RequestPart(required = false) MultipartFile avatar) {
        UserCreateDTO userCreateDTO = userService.readJson(user, avatar);
        return ResponseEntity.ok(userService.updateUser(userCreateDTO));
    }
    @PutMapping(value = "changePassword")
    public ResponseEntity<?> updatePassword(Authentication authentication, @RequestBody UserChangePassDTO userChangePassDTO) {
        Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        HashMap<String, String> hashMap = new HashMap<>();
        if (userService.changePassword(username, userChangePassDTO)) {

            hashMap.put("message", "Success");

            return ResponseEntity.ok(hashMap);
        }
        hashMap.put("message", "Failed");
        return ResponseEntity.ok(hashMap);
    }

    @GetMapping(value = "getAllMerchant")
    public ResponseEntity<?> getAllMerchant(@RequestParam HashMap<String, String> hashMap) {
        int no = Integer.parseInt(hashMap.getOrDefault("no", "0"));
        int limit = Integer.parseInt(hashMap.getOrDefault("limit", "20"));
        return ResponseEntity.ok(userService.getAllMerchant(no, limit));
    }

    @GetMapping(value = "getAllFarmer")
    public ResponseEntity<?> getAllFarmer(@RequestParam HashMap<String, String> hashMap) {
        int no = Integer.parseInt(hashMap.getOrDefault("no", "0"));
        int limit = Integer.parseInt(hashMap.getOrDefault("limit", "20"));
        return ResponseEntity.ok(userService.getAllFarmer(no, limit));
    }

    @GetMapping(value = "searchMerchant")
    public ResponseEntity<?> searchMerchant(@RequestParam HashMap<String, String> hashMap) {
        String username = hashMap.getOrDefault("username", "");
        int no = Integer.parseInt(hashMap.getOrDefault("no", "0"));
        int limit = Integer.parseInt(hashMap.getOrDefault("limit", "20"));
        return ResponseEntity.ok(userService.searchMerchant(no, limit, username));
    }

    @GetMapping(value = "searchFarmer")
    public ResponseEntity<?> searchFarmer(@RequestParam HashMap<String, String> hashMap) {
        String username = hashMap.getOrDefault("username", "");
        int no = Integer.parseInt(hashMap.getOrDefault("no", "0"));
        int limit = Integer.parseInt(hashMap.getOrDefault("limit", "20"));
        return ResponseEntity.ok(userService.searchFarmer(no, limit, username));
    }

    @GetMapping(value = "getMerchantDetail/{id}")
    public  ResponseEntity<?> getMerchantDetail(@PathVariable int id, @RequestParam HashMap<String, String> hashMap) {
        int no = Integer.parseInt(hashMap.getOrDefault("no", "0"));
        int limit = Integer.parseInt(hashMap.getOrDefault("limit", "20"));

        return ResponseEntity.ok(userService.getMerchantDetail(id, no, limit));
    }
}
