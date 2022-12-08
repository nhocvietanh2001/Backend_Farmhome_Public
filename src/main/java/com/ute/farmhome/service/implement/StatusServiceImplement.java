package com.ute.farmhome.service.implement;

import com.ute.farmhome.entity.StatusProduct;
import com.ute.farmhome.exception.ResourceNotFound;
import com.ute.farmhome.repository.StatusProductRepository;
import com.ute.farmhome.repository.StatusUserRepository;
import com.ute.farmhome.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImplement implements StatusService {
    @Autowired
    StatusProductRepository statusProductRepository;
    @Autowired
    StatusUserRepository statusUserRepository;
    @Override
    public StatusProduct getPendingStatusProduct() {
        return statusProductRepository.findById(1).orElseThrow(() -> new ResourceNotFound("Status Product", "id", "1"));
    }

    @Override
    public StatusProduct getDealingStatusProduct() {
        return statusProductRepository.findById(2).orElseThrow(() -> new ResourceNotFound("Status Product", "id", "2"));
    }
}
