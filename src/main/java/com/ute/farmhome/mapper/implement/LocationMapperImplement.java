package com.ute.farmhome.mapper.implement;

import com.ute.farmhome.dto.LocationDTO;
import com.ute.farmhome.entity.Location;
import com.ute.farmhome.mapper.DistrictMapper;
import com.ute.farmhome.mapper.LocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocationMapperImplement implements LocationMapper {
    @Autowired
    private DistrictMapper districtMapper;
    @Override
    public Location map(LocationDTO locationDTO) {
        Location entity = new Location();
        entity.setId(locationDTO.getId());
        entity.setAddress(locationDTO.getAddress());
        entity.setDistrict(districtMapper.map(locationDTO.getDistrict()));
        return entity;
    }
}
