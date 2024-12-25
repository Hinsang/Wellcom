package com.Wellcom.service;

import com.Wellcom.domain.dto.ProductDto;
import com.Wellcom.domain.entity.ProductEntity;
import com.Wellcom.domain.entity.ProductRepository;
import com.Wellcom.domain.entity.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SearchService {

    private final SearchRepository searchRepository;

    @Autowired
    public SearchService(SearchRepository searchRepository) {

        this.searchRepository = searchRepository;

    }

    public List<ProductEntity> getSearch(String text) {

        List<ProductEntity> productEntity = searchRepository.getSearch(text);

        return productEntity;

    }

}
