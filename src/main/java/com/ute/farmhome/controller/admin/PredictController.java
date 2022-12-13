package com.ute.farmhome.controller.admin;

import com.ute.farmhome.service.ClassifyImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/tensorflow")
public class PredictController {
    @Autowired
    ClassifyImageService classifyImageService;

    @PostMapping(value = "/classify", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    @CrossOrigin(origins = "*")
    public ClassifyImageService.LabelWithProbability classifyImage(@RequestPart MultipartFile file) throws IOException {
        return classifyImageService.classifyImage(file.getBytes());
    }
}
