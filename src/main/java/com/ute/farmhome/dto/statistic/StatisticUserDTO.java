package com.ute.farmhome.dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatisticUserDTO {
    String firstName;
    String lastName;
    float total;
    public StatisticUserDTO(Object[] objects) {
        this.firstName = (String) objects[0];
        this.lastName = (String) objects[1];
        this.total = Float.parseFloat(String.valueOf(objects[2]));
    }
}
