package com.ute.farmhome.dto;

import com.ute.farmhome.entity.District;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LocationDTO {
    private int id;
    private String address;
    private DistrictDTO district;
}
