package com.ute.farmhome.mapper.implement;

import com.ute.farmhome.dto.ProvinceDTO;
import com.ute.farmhome.entity.Province;
import com.ute.farmhome.mapper.ProvinceMapper;
import com.ute.farmhome.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProvinceMapperImplement implements ProvinceMapper {
    @Override
    public Province map(ProvinceDTO dto) {
        Province entity = new Province();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }

    @Override
    public ProvinceDTO map(Province entity) {
        ProvinceDTO dto = new ProvinceDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}
