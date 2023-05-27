package com.ute.farmhome.dto.statistic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Stats {
    Long totalUser;
    Long totalMerchant;
    Long totalFarmer;
    Long newThisMonth;
}
