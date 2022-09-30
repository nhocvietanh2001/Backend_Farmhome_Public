package com.ute.farmhome.dto;

import com.ute.farmhome.entity.Role;
import com.ute.farmhome.entity.StatusUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserShowDTO {
    private int id;
    private String username;
    private String avatar;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate createDate;
    private StatusUser status;
    private Role role;
}
