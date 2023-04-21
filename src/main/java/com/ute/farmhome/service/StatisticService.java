package com.ute.farmhome.service;

import com.ute.farmhome.dto.StatisticDTO;

import java.time.LocalDate;
import java.util.List;

public interface StatisticService {
    StatisticDTO statisticPieChart(LocalDate startDate, LocalDate endDate);
    StatisticDTO statisticBarChart(LocalDate startDate, LocalDate endDate);
}
