package com.ute.farmhome.controller.admin;

import com.ute.farmhome.entity.Role;
import com.ute.farmhome.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/role")
public class RoleControllerAdmin {
    @Autowired
    private RoleRepository roleRepository;
    @PostMapping("")
    public Role createRole(@RequestBody Role role) {
        return roleRepository.save(role);
    }
}
