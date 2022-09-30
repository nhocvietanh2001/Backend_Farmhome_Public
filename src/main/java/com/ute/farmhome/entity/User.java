package com.ute.farmhome.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String avatar;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate createDate;
    @OneToOne
    @JoinColumn(name = "Status_id")
    private StatusUser status;
    @OneToOne
    @JoinColumn(name = "Role_Id")
    private Role role;
}
