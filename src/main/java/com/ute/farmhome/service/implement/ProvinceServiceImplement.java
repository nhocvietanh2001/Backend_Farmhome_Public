package com.ute.farmhome.service.implement;

import com.ute.farmhome.dto.ProvinceDTO;
import com.ute.farmhome.entity.Province;
import com.ute.farmhome.mapper.ProvinceMapper;
import com.ute.farmhome.repository.ProvinceRepository;
import com.ute.farmhome.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinceServiceImplement implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private ProvinceMapper provinceMapper;
    @Override
    public ProvinceDTO save(ProvinceDTO dto) {
        Province province = provinceMapper.map(dto);
        return provinceMapper.map(provinceRepository.save(province));
    }

    @Override
    public ProvinceDTO update(ProvinceDTO dto) {
        Province province = provinceMapper.map(dto);
        return provinceMapper.map(provinceRepository.save(province));
    }

    @Override
    public void deleteById(int id) {
        provinceRepository.deleteById(id);
    }
}
