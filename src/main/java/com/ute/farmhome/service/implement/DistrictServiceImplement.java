package com.ute.farmhome.service.implement;

import com.ute.farmhome.dto.DistrictDTO;
import com.ute.farmhome.entity.District;
import com.ute.farmhome.entity.Province;
import com.ute.farmhome.exception.ResourceNotFound;
import com.ute.farmhome.mapper.DistrictMapper;
import com.ute.farmhome.repository.DistrictRepository;
import com.ute.farmhome.repository.ProvinceRepository;
import com.ute.farmhome.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictServiceImplement implements DistrictService {
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private DistrictMapper districtMapper;
    @Override
    public DistrictDTO save(DistrictDTO districtDTO) {
        District district = districtMapper.map(districtDTO);
        Province province = provinceRepository.findById(district.getProvince().getId())
                .orElseThrow(() -> new ResourceNotFound("Province", "id", String.valueOf(district.getProvince().getId())));
        district.setProvince(province);
        return districtMapper.map(districtRepository.save(district));
    }

    @Override
    public DistrictDTO update(DistrictDTO districtDTO) {
        District district = districtMapper.map(districtDTO);

        return districtMapper.map(districtRepository.save(district));
    }

    @Override
    public void deleteById(int id) {
        districtRepository.deleteById(id);
    }

    @Override
    public District findById(int id) {
        return districtRepository.findById(id).orElseThrow(() -> new ResourceNotFound("District", "id", String.valueOf(id)));
    }

}
