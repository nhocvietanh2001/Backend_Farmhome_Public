package com.ute.farmhome.service.implement;

import com.ute.farmhome.entity.FruitImage;
import com.ute.farmhome.exception.ResourceNotFound;
import com.ute.farmhome.repository.FruitImageRepository;
import com.ute.farmhome.service.FruitImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FruitImageServiceImplement implements FruitImageService {
    @Autowired
    private FruitImageRepository fruitImageRepository;

    @Override
    public void deleteImageById(int id) {
        FruitImage fruitImage = fruitImageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("FruitImage", "id", String.valueOf(id)));
        fruitImage.setFruit(null);
        fruitImageRepository.deleteById(id);
    }
}
