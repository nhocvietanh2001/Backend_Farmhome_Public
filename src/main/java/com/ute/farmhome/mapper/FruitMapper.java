package com.ute.farmhome.mapper;

import com.ute.farmhome.dto.FruitDTO;
import com.ute.farmhome.dto.FruitShowDTO;
import com.ute.farmhome.entity.Fruit;
import org.mapstruct.Mapper;

@Mapper
public interface FruitMapper {
    Fruit map(FruitDTO dto);
    FruitDTO map(Fruit fruit);
    FruitShowDTO mapToShow(Fruit fruit);
}
