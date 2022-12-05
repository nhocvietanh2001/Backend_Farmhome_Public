package com.ute.farmhome.service.implement;

import com.ute.farmhome.dto.WardDTO;
import com.ute.farmhome.entity.District;
import com.ute.farmhome.entity.Ward;
import com.ute.farmhome.exception.ResourceNotFound;
import com.ute.farmhome.mapper.WardMapper;
import com.ute.farmhome.repository.DistrictRepository;
import com.ute.farmhome.repository.WardRepository;
import com.ute.farmhome.service.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WardServiceImplement implements WardService {
    @Autowired
    DistrictRepository districtRepository;
    @Autowired
    WardRepository wardRepository;
    @Autowired
    WardMapper wardMapper;
    @Override
    public WardDTO save(WardDTO wardDTO) {
        Ward ward = wardMapper.map(wardDTO);
        District district = districtRepository.findById(wardDTO.getDistrict().getId())
                .orElseThrow(() -> new ResourceNotFound("district", "id", String.valueOf(ward.getDistrict().getId())));
        ward.setDistrict(district);
        return wardMapper.map(wardRepository.save(ward));
    }

    @Override
    public WardDTO update(WardDTO wardDTO) {
        Ward ward = wardMapper.map(wardDTO);

        return wardMapper.map(wardRepository.save(ward));
    }

    @Override
    public void deleteById(int id) {
        wardRepository.deleteById(id);
    }

    @Override
    public Ward findById(int id) {
        return wardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("ward", "id", String.valueOf(id)));
    }
}
