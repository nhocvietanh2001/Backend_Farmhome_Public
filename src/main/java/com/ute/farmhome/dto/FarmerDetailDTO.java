package com.ute.farmhome.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FarmerDetailDTO {
    UserShowDTO user;
    List<HistoryDTO> historyList;
    List<FruitShowDTO> fruitList;
    List<OrderDTO> orderList;
}
