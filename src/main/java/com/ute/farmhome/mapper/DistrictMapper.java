package com.ute.farmhome.mapper;

import com.ute.farmhome.dto.DistrictDTO;
import com.ute.farmhome.entity.District;
import org.mapstruct.Mapper;

@Mapper
public interface DistrictMapper {
    District map(DistrictDTO districtDTO);
    DistrictDTO map(District entity);
}
