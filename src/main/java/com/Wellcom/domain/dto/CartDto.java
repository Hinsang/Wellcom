package com.Wellcom.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartDto {

    private List<Integer> pid;
    private String ptitle;
    private String pcontent;
    private MultipartFile pfile;    // 파일을 처음에 받을 때
    private int pprice;
    private int plike;
    private String ptype;
    private String productImageUrl; // 파일을 엔티티에 등록할 때
    private LocalDateTime pdate;

}
