package com.ute.farmhome.dto;

import com.ute.farmhome.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FruitShowDTO {
    int id;
    String name;
    float weight;
    String unit;
    MultipartFile imageFile;
    String image;
    LocalDate date;
    UserShowDTO farmer;
}
