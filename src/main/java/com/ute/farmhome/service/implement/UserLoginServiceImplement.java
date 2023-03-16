package com.ute.farmhome.service.implement;

import com.ute.farmhome.entity.User;
import com.ute.farmhome.entity.UserLogin;
import com.ute.farmhome.repository.UserLoginRepository;
import com.ute.farmhome.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImplement implements UserLoginService {
    @Autowired
    UserLoginRepository userLoginRepository;
    @Override
    public UserLogin save(User user, String deviceId) {
        UserLogin userLogin = new UserLogin();
        if (userLoginRepository.findByDeviceId(deviceId).isPresent()) {
            userLogin.setId(userLoginRepository.findByDeviceId(deviceId).get().getId());
        }
        userLogin.setUser(user);
        userLogin.setDeviceId(deviceId);
        userLogin = userLoginRepository.save(userLogin);
        return userLogin;
    }
}
