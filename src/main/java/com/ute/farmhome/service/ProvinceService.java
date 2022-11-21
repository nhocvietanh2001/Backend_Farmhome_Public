package com.ute.farmhome.service;

import com.ute.farmhome.dto.ProvinceDTO;

public interface ProvinceService {
    ProvinceDTO save(ProvinceDTO dto);
    ProvinceDTO update(ProvinceDTO dto);
    void deleteById(int id);
}
