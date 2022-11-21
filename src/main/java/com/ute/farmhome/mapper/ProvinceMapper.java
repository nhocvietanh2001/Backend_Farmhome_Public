package com.ute.farmhome.mapper;

import com.ute.farmhome.dto.ProvinceDTO;
import com.ute.farmhome.entity.Province;
import org.mapstruct.Mapper;

@Mapper
public interface ProvinceMapper {
    Province map(ProvinceDTO provinceDTO);
    ProvinceDTO map(Province entity);
}
