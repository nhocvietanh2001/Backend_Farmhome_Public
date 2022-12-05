package com.ute.farmhome.service;

import com.ute.farmhome.dto.DistrictDTO;
import com.ute.farmhome.dto.WardDTO;
import com.ute.farmhome.entity.District;
import com.ute.farmhome.entity.Ward;

public interface WardService {
    WardDTO save(WardDTO wardDTO);
    WardDTO update(WardDTO wardDTO);
    void deleteById(int id);
    Ward findById(int id);
}
