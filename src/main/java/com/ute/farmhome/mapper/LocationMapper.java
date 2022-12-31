package com.ute.farmhome.mapper;

import com.ute.farmhome.dto.LocationDTO;
import com.ute.farmhome.entity.Location;
import org.mapstruct.Mapper;

@Mapper
public interface LocationMapper {
    Location map(LocationDTO locationDTO);
    LocationDTO map(Location entity);

}
