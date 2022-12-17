package com.ute.farmhome.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ute.farmhome.entity.Location;
import com.ute.farmhome.entity.Role;
import com.ute.farmhome.entity.StatusUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserCreateDTO {
    private int id;
    private String username;
    private String password;
    private String confirmPassword;
    private String newPassword;
    private MultipartFile avatarFile;
    private String avatar;
    private String firstName;
    private String lastName;
    private String birthDay;
    private String email;
    private String phone;
    private String description;
    private LocationDTO location;
    private LocalDate createDate;
    private StatusUser status;
    private Collection<Role> roles = new ArrayList<>();
}
