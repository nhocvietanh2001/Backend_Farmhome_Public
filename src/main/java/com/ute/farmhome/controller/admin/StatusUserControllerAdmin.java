package com.ute.farmhome.controller.admin;

import com.ute.farmhome.entity.StatusUser;
import com.ute.farmhome.repository.StatusUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/status")
public class StatusUserControllerAdmin {
    @Autowired
    private StatusUserRepository statusUserRepository;
    @PostMapping()
    public StatusUser createStatusUser(@RequestBody StatusUser statusUser) {
        return statusUserRepository.save(statusUser);
    }
}
