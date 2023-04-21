package com.ute.farmhome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatisticDateDTO {
    LocalDate date;
    float total;
    public StatisticDateDTO(Object[] objects) {
        this.date = (LocalDate) objects[0];
        this.total = Float.parseFloat(String.valueOf(objects[1]));
    }
}
