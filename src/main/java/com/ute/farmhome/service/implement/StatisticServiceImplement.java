package com.ute.farmhome.service.implement;

import com.ute.farmhome.dto.statistic.*;
import com.ute.farmhome.repository.HistoryRepository;
import com.ute.farmhome.repository.UserRepository;
import com.ute.farmhome.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticServiceImplement implements StatisticService {
    @Autowired
    HistoryRepository historyRepository;
    @Autowired
    UserRepository userRepository;
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

    @Override
    public StatisticDTO statisticMerchant() {
        List<Object[]> results = userRepository.statisticMerchant();
        List<StatisticUserDTO> statisticUserDTOS = results.stream().map(StatisticUserDTO::new).toList();
        float summary = (float) statisticUserDTOS.stream().mapToDouble(StatisticUserDTO::getTotal).sum();
        return new StatisticDTO(statisticUserDTOS, summary);
    }

    @Override
    public StatisticDTO statisticFarmer() {
        List<Object[]> results = userRepository.statisticFarmer();
        List<StatisticUserDTO> statisticUserDTOS = results.stream().map(StatisticUserDTO::new).toList();
        float summary = (float) statisticUserDTOS.stream().mapToDouble(StatisticUserDTO::getTotal).sum();
        return new StatisticDTO(statisticUserDTOS, summary);
    }

    @Override
    public StatisticMultipleListDTO statisticUser() {
        List<Object[]> farmers = userRepository.statisticFarmer();
        List<Object[]> merchants = userRepository.statisticMerchant();
        List<StatisticUserDTO> statisticFarmerList = farmers.stream().map(StatisticUserDTO::new).toList();
        List<StatisticUserDTO> statisticMerchantList = merchants.stream().map(StatisticUserDTO::new).toList();
        float summary = (float) statisticFarmerList.stream().mapToDouble(StatisticUserDTO::getTotal).sum();
        return new StatisticMultipleListDTO(statisticFarmerList, statisticMerchantList, summary);
    }

    @Override
    public StatisticDTO statisticDate(int day) {
        List<Object[]> data = historyRepository.statisticBackInDay(LocalDate.now().minusDays(day));
        List<StatisticDateDTO> statisticDateDTOS = new ArrayList<>(data.stream().map(StatisticDateDTO::new).toList());
        LocalDate startDate = LocalDate.now().minusDays(day);

        Map<LocalDate, Float> map = new HashMap<>();

        for (StatisticDateDTO statisticDateDTO : statisticDateDTOS) {
            map.put(statisticDateDTO.getDate(), statisticDateDTO.getTotal());
        }

        while (!startDate.isAfter(LocalDate.now())) {
            if (!map.containsKey(startDate)) {
                statisticDateDTOS.add(new StatisticDateDTO(startDate, 0));
            }
            startDate = startDate.plusDays(1);
        }

        float summary = (float) statisticDateDTOS.stream().mapToDouble(StatisticDateDTO::getTotal).sum();
        return new StatisticDTO(statisticDateDTOS, summary);
    }

}
