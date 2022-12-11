package com.ute.farmhome.controller.admin;

import com.ute.farmhome.entity.News;
import com.ute.farmhome.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/admin/news")
public class NewsControllerAdmin {
    @Autowired
    NewsService newsService;
    @PostMapping("/create")
    public ResponseEntity<?> createNews(@RequestBody News news) {
        return new ResponseEntity(newsService.createNews(news), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable int id) {
        return ResponseEntity.ok(newsService.getNewsById(id));
    }
    @GetMapping()
    public ResponseEntity<?> getAll(@RequestParam HashMap<String, String> hashMap) {
        int no = Integer.parseInt(hashMap.getOrDefault("no", "0"));
        int limit = Integer.parseInt(hashMap.getOrDefault("limit", "20"));

        return ResponseEntity.ok(newsService.getAllNews(no, limit));
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody News news) {
        return ResponseEntity.ok(newsService.updateNews(news));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("message", "deleted");
        return ResponseEntity.ok(hashMap);
    }
}
