package com.Wellcom.service;

import com.Wellcom.config.auth.PrincipalDetails;
import com.Wellcom.domain.dto.ProductDto;
import com.Wellcom.domain.dto.ReviewDto;
import com.Wellcom.domain.dto.ReviewResponseDto;
import com.Wellcom.domain.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    // ☆ 공부하다가 생성자 주입에 관한 이해 정리 ☆
    // 1. private final ProductRepository productRepository;는 단순히 선언된 수정 불가능한 필드이다
    // 2. @Autowired를 통해서 @Bean에 등록된 @Repository 정보를 가져온다
    // 3. public ProductService(ProductRepository productRepository)를 통해서 선언된 필드를 받는다
    // 4. this.productRepository는 선언된 필드이다
    // 5. = productRepository; 이 부분은 @Bean에 등록된 productRepository를 가져와서 등록하는 부분이다.
    // 6. 이로써 productRepository의 메서드들을 사용할 수 있다

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ReviewRepository reviewRepository, MemberRepository memberRepository) {
        this.productRepository = productRepository;
        this.reviewRepository = reviewRepository;
        this.memberRepository = memberRepository;
    }

    @Value("${file.path}") // application.properties에 설정된 file.path 경로가 그대로 들어온다
    private String uploadFolder; // 설정한 경로를 변수에 저장한다

    @Transactional
    public boolean setUpload(ProductDto productDto, PrincipalDetails principalDetails) {

        UUID uuid = UUID.randomUUID(); // 파일에 고유의 랜덤 ID 부여
        String productFileName = uuid + "_" + productDto.getPfile().getOriginalFilename();
        System.out.println("상품 이름 : " + productFileName);

        Path productFilePath = Paths.get(uploadFolder + productFileName); // 저장될 경로와 파일 이름

        try {

            // 실제 파일을 서버 폴더에 저장한다. 각각의 파라미터에 파일 경로와 파일을 Byte화 해서 넣어준다.
            Files.write(productFilePath, productDto.getPfile().getBytes());

            ProductEntity productEntity = productDto.toEntity(productFileName, principalDetails.getMemberEntity());
            productRepository.save(productEntity);

            return true;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }

    }

    public List<ProductDto> loadProducts() {

        try {

            List<ProductEntity> productEntityList = productRepository.findAllDesc();
            List<ProductDto> productDtoList = new ArrayList<>();
            productEntityList.forEach(e -> productDtoList.add(e.toDto()));
            System.out.println(productDtoList);
            return productDtoList;

        } catch (Exception e) {

            e.printStackTrace();
            return null;

        }

    }

    // 상품 개별 조회
    public ProductDto productDetail(int pid) {

        Optional<ProductEntity> optional = productRepository.findByPid(pid);

        if(optional.isPresent()) {

            ProductEntity productEntity = optional.get();
            ProductDto productDto = productEntity.toDto();
            return productDto;

        } else {

            return null;

        }

    }

    @Transactional
    public List<ProductDto> getCartList(List<Integer> pids) {

        List<ProductEntity> productEntityList = productRepository.findByPidIn(pids);
        List<ProductDto> productDtoList = new ArrayList<>();
        productEntityList.forEach(e -> productDtoList.add(e.toDto()));
        System.out.println(productDtoList+"@@@@@@@@@@@@@@@@");
        return productDtoList;

    }

    @Transactional
    public boolean postReview(ReviewDto reviewDto) {

        MemberEntity memberEntity = memberRepository.findByMid(reviewDto.getMid());
        Optional<ProductEntity> optionalProductEntity = productRepository.findByPid(reviewDto.getPid());

        if(optionalProductEntity.isPresent()) {

            ProductEntity productEntity = optionalProductEntity.get();
            ReviewEntity reviewEntity = reviewDto.toEntity(memberEntity, productEntity);

            reviewRepository.save(reviewEntity);

        } else {

            return false;

        }

        return true;

    }

    public List<ReviewResponseDto> loadReview(int pid) {

        List<Object[]> result = reviewRepository.findByPid(pid);
        List<ReviewResponseDto> reviewResponseDtos = new ArrayList<>();

        for(int i = 0 ; i < result.size() ; i++) {

            int rid = (int) result.get(i)[0];
            int stars = (int) result.get(i)[1];
            String comment = (String) result.get(i)[2];
            // 테이블의 DATETIME 자료형을 Timestamp로 받아서 LocalDateTime으로 변환
            Timestamp timestamp = (Timestamp) result.get(i)[3];
            LocalDateTime date = timestamp.toLocalDateTime();
            String name = (String) result.get(i)[4];

            ReviewResponseDto reviewResponseDto = new ReviewResponseDto(rid, stars, comment, date, name);

            reviewResponseDtos.add(reviewResponseDto);

        }

        return reviewResponseDtos;

    }

}
