package com.ute.farmhome.dto;

import com.ute.farmhome.entity.StatusProduct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    int id;
    FruitDTO fruit;
    UserShowDTO farmer;
    UserShowDTO merchant;
    Float price;
    String date;
    Boolean transport;
    StatusProduct status;
}
