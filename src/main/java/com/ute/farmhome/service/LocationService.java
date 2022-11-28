package com.ute.farmhome.service;

import com.ute.farmhome.dto.LocationDTO;
import com.ute.farmhome.entity.Location;

public interface LocationService {
    Location bindData(LocationDTO dto);
    Location bindUpdateData(LocationDTO dto);
}
