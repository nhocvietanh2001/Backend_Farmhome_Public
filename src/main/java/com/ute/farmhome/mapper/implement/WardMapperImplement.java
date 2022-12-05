package com.ute.farmhome.mapper.implement;

import com.ute.farmhome.dto.WardDTO;
import com.ute.farmhome.entity.Ward;
import com.ute.farmhome.mapper.DistrictMapper;
import com.ute.farmhome.mapper.WardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WardMapperImplement implements WardMapper {
    @Autowired
    private DistrictMapper districtMapper;
    @Override
    public Ward map(WardDTO wardDTO) {
        Ward ward = new Ward();
        ward.setId(wardDTO.getId());
        ward.setName(wardDTO.getName());
        //ward.setDistrict(districtMapper.map(wardDTO.getDistrict()));
        return ward;
    }

    @Override
    public WardDTO map(Ward entity) {
        WardDTO wardDTO = new WardDTO();
        wardDTO.setId(entity.getId());
        wardDTO.setName(entity.getName());
        wardDTO.setDistrict(districtMapper.map(entity.getDistrict()));
        return wardDTO;
    }
}
