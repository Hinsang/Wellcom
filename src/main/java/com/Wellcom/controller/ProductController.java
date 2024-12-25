package com.Wellcom.controller;

import com.Wellcom.config.auth.PrincipalDetails;
import com.Wellcom.domain.dto.CartDto;
import com.Wellcom.domain.dto.ProductDto;
import com.Wellcom.domain.dto.ReviewDto;
import com.Wellcom.domain.dto.ReviewResponseDto;
import com.Wellcom.domain.entity.ReviewEntity;
import com.Wellcom.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/setUpload")
    public boolean setUpload(ProductDto productDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        if(productDto.getPfile().isEmpty()) {
            throw new IllegalArgumentException("상품 이미지가 없습니다.");
        }

        System.out.println(productDto);

        return productService.setUpload(productDto, principalDetails);

    }

    @GetMapping("/loadProducts")
    public List<ProductDto> loadProducts() {

        return productService.loadProducts();

    }

    @GetMapping("/detail")
    public ProductDto productDetail(@RequestParam("pid") int pid) {

        return productService.productDetail(pid);

    }

    @PostMapping("/cartList")
    public List<ProductDto> getCartList(@RequestBody CartDto cartList) {

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
        return productService.getCartList(cartList.getPid());

    }

    @PostMapping("/postReview")
    public boolean postReview(@RequestBody ReviewDto reviewDto) {

        if(reviewDto.getDate() == null) {
            reviewDto.setDate(LocalDateTime.now());
        }

        System.out.println(reviewDto);
        productService.postReview(reviewDto);

        return true;

    }

    @GetMapping("/loadReview")
    public List<ReviewResponseDto> loadReview(@RequestParam("pid") int pid) {

        return productService.loadReview(pid);

    }

}
