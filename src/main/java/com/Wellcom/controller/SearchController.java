package com.Wellcom.controller;

import com.Wellcom.domain.dto.ProductDto;
import com.Wellcom.domain.entity.ProductEntity;
import com.Wellcom.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {

        this.searchService = searchService;

    }

    @GetMapping("/api/search")
    public List<ProductEntity> getSearch(@RequestParam(value = "text", required = false) String text) {

        System.out.println(text + "Very good!!");
        return searchService.getSearch(text);

    }

}
