package com.ute.farmhome.dto;

import lombok.Data;

import java.util.List;

@Data

public class JwtResponse {
    private String token;
    private String type="Bearer";
    private String username;
    private List<String> roles;
    private String avatar;
    private long idUser;
    public JwtResponse(String token, String username, List<String> roles, String avatar, long id) {
        this.token = token;
        this.roles = roles;
        this.username = username;
        this.avatar = avatar;
        this.idUser = id;
    }
}
