package com.ute.farmhome.service;

import com.ute.farmhome.dto.statistic.StatisticDTO;
import com.ute.farmhome.dto.statistic.StatisticMultipleListDTO;
import com.ute.farmhome.dto.statistic.Stats;

import java.time.LocalDate;

public interface StatisticService {
    StatisticDTO statisticPieChart(LocalDate startDate, LocalDate endDate);
    StatisticDTO statisticBarChart(LocalDate startDate, LocalDate endDate);
    StatisticDTO statisticMerchant();
    StatisticDTO statisticFarmer();
    StatisticMultipleListDTO statisticUser();
    StatisticDTO statisticDate(int day);
    Stats getStats();
}
