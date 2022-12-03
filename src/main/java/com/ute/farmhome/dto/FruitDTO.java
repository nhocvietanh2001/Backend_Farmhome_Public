package com.ute.farmhome.dto;

import com.ute.farmhome.entity.FruitImage;
import com.ute.farmhome.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FruitDTO {
    int id;
    String name;
    float weight;
    String unit;
    List<MultipartFile> imageFiles = new ArrayList<>();
    List<FruitImage> images = new ArrayList<>();
    String description;
    String date;
    User farmer;
    String season;
    Boolean popular;
}
