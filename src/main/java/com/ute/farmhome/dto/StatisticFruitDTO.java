package com.ute.farmhome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatisticFruitDTO {
    String name;
    float total;
    float percent;
    public StatisticFruitDTO(Object[] objects) {
        this.name = (String) objects[0];
        this.total = Float.parseFloat(String.valueOf(objects[1]));
    }
}
