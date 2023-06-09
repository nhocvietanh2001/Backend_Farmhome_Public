package com.ute.farmhome.controller.common;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.ute.farmhome.dto.NotificationNote;
import com.ute.farmhome.dto.UserShowDTO;
import com.ute.farmhome.service.FirebaseMessagingService;
import com.ute.farmhome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FirebaseMessagingService firebaseMessagingService;
    @GetMapping("/profile")
    public UserShowDTO getProfile(Authentication authentication) {
        Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userService.getByUsername(username);
    }
    @GetMapping("username/{username}")
    public UserShowDTO getByUsername(@PathVariable String username) {
        return userService.getByUsername(username);
    }
    @GetMapping("id/{id}")
    public UserShowDTO getById(@PathVariable int id) {
        return userService.getById(id);
    }
    @PostMapping("/send-notification")
    public String sendNotification(@RequestBody NotificationNote note,
                                   @RequestParam String token) throws FirebaseMessagingException {
        return firebaseMessagingService.sendNotification(note, token);
    }
}
