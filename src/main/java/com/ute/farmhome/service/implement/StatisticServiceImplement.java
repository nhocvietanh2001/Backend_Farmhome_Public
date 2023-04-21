package com.ute.farmhome.service.implement;

import com.ute.farmhome.dto.StatisticDTO;
import com.ute.farmhome.dto.StatisticDateDTO;
import com.ute.farmhome.dto.StatisticFruitDTO;
import com.ute.farmhome.repository.HistoryRepository;
import com.ute.farmhome.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticServiceImplement implements StatisticService {
    @Autowired
    HistoryRepository historyRepository;
    @Override
    public StatisticDTO statisticPieChart(LocalDate startDate, LocalDate endDate) {
        List<Object[]> results = historyRepository.statisticFruitByDate(startDate, endDate);
        List<StatisticFruitDTO> statisticFruitDTOS = results.stream().map(StatisticFruitDTO::new).toList();
        float summary = (float) statisticFruitDTOS.stream().mapToDouble(StatisticFruitDTO::getTotal).sum();
        statisticFruitDTOS.forEach(statisticFruitDTO -> {statisticFruitDTO.setPercent(statisticFruitDTO.getTotal() / summary * 100);});
        return new StatisticDTO(statisticFruitDTOS, summary);
    }

    @Override
    public StatisticDTO statisticBarChart(LocalDate startDate, LocalDate endDate) {
        List<Object[]> results = historyRepository.statisticByDate(startDate, endDate);
        List<StatisticDateDTO> statisticDateDTOS = results.stream().map(StatisticDateDTO::new).toList();
        float summary = (float) statisticDateDTOS.stream().mapToDouble(StatisticDateDTO::getTotal).sum();
        return new StatisticDTO(statisticDateDTOS, summary);
    }
}
