package com.ute.farmhome.service.implement;

import com.ute.farmhome.dto.LocationDTO;
import com.ute.farmhome.entity.Location;
import com.ute.farmhome.exception.ResourceNotFound;
import com.ute.farmhome.repository.LocationRepository;
import com.ute.farmhome.service.DistrictService;
import com.ute.farmhome.service.LocationService;
import com.ute.farmhome.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImplement implements LocationService {
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private WardService wardService;
    @Override
    public Location bindData(LocationDTO dto) {
        Location location = new Location();
        location.setAddress(dto.getAddress());
        location.setWard(wardService.findById(dto.getWard().getId()));
        return location;
    }

    @Override
    public Location bindUpdateData(LocationDTO dto) {
        Location location = locationRepository.findById(dto.getId())
                .orElse(new Location());
        location.setAddress(dto.getAddress());
        location.setWard(wardService.findById(dto.getWard().getId()));
        return location;
    }

    @Override
    public Location findById(int id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Location", "id", String.valueOf(id)));
    }

}
