package com.ute.farmhome.dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StatisticMultipleListDTO {
    List<?> farmer;
    List<?> merchant;
    float summary;
}
