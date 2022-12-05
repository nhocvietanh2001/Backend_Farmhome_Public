package com.ute.farmhome.mapper;

import com.ute.farmhome.dto.WardDTO;
import com.ute.farmhome.entity.Ward;

public interface WardMapper {
    Ward map(WardDTO wardDTO);
    WardDTO map(Ward entity);
}
