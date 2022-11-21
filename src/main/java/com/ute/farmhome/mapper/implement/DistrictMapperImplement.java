package com.ute.farmhome.mapper.implement;

import com.ute.farmhome.dto.DistrictDTO;
import com.ute.farmhome.entity.District;
import com.ute.farmhome.mapper.DistrictMapper;
import com.ute.farmhome.mapper.ProvinceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DistrictMapperImplement implements DistrictMapper {
    @Autowired
    private ProvinceMapper provinceMapper;
    @Override
    public District map(DistrictDTO districtDTO) {
        District district = new District();
        district.setId(districtDTO.getId());
        district.setName(districtDTO.getName());
        district.setProvince(provinceMapper.map(districtDTO.getProvince()));
        return district;
    }

    @Override
    public DistrictDTO map(District entity) {
        DistrictDTO dto = new DistrictDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setProvince(provinceMapper.map(entity.getProvince()));
        return dto;
    }
}
