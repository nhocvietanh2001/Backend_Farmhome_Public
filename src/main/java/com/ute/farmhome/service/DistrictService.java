package com.ute.farmhome.service;


import com.ute.farmhome.dto.DistrictDTO;
import com.ute.farmhome.entity.District;

public interface DistrictService {
    DistrictDTO save(DistrictDTO districtDTO);
    DistrictDTO update(DistrictDTO districtDTO);
    void deleteById(int id);
    District findById(int id);
}
