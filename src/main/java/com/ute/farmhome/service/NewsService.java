package com.ute.farmhome.service;

import com.ute.farmhome.dto.PaginationDTO;
import com.ute.farmhome.entity.News;
import org.springframework.web.multipart.MultipartFile;

public interface NewsService {
    News createNews(News news, MultipartFile imageBanner, MultipartFile imageContent);
    News getNewsById(int id);
    PaginationDTO getAllNews(int no, int limit);
    News updateNews(News news);
    void deleteNews(int id);
}
