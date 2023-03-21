package com.ute.farmhome.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.tensorflow.Graph;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@Slf4j
public class Config {
    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(new ClassPathResource("farmhomemessage.json").getInputStream());
        FirebaseOptions firebaseOptions = FirebaseOptions
                .builder()
                .setCredentials(googleCredentials)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "FarmHomeMessage");
        return FirebaseMessaging.getInstance(app);
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2A,10);
    }
//    @Bean
//    public Graph tfModelGraph(@Value("${fruitdetect.model}") String tfModelPath) throws IOException {
//        Resource graphResource = getResource(tfModelPath);
//        Graph graph = new Graph();
//        graph.importGraphDef(IOUtils.toByteArray(graphResource.getInputStream()));
//        log.info("Loaded Tensorflow model");
//        return graph;
//    }
//
//    private Resource getResource(@Value("${tf.frozenModelPath}") String tfModelPath) {
//        Resource graphResource = new FileSystemResource(tfModelPath);
//        if(!graphResource.exists()) {
//            graphResource = new ClassPathResource(tfModelPath);
//        }
//        if(!graphResource.exists()) {
//            throw new IllegalArgumentException(String.format("File %s does not exist", tfModelPath));
//        }
//        return graphResource;
//    }
//
//    @Bean
//    public List<String> tfModelLabels(@Value("${fruitdetect.labels}") String labelsPath) throws IOException {
//        Resource labelsRes = getResource(labelsPath);
//        log.info("Loaded model labels");
//        return IOUtils.readLines(labelsRes.getInputStream(), Charset.forName("UTF-8")).stream()
//                .map(label -> label.substring(label.contains(":") ? label.indexOf(":")+1 : 0)).collect(Collectors.toList());
//    }
}
