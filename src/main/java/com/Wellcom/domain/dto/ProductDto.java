package com.Wellcom.domain.dto;

import com.Wellcom.domain.entity.MemberEntity;
import com.Wellcom.domain.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductDto {

    private int pid;
    private String ptitle;
    private String pcontent;
    private MultipartFile pfile;    // 파일을 처음에 받을 때
    private int pprice;
    private int plike;
    private String ptype;
    private String productImageUrl; // 파일을 엔티티에 등록할 때
    private LocalDateTime pdate;
    // mid를 따로 등록할 필요가 없다. 서비스에서 findById를 해서 아이디 값을 포함한 멤버 객체를 직접 등록하면 된다.

    public ProductEntity toEntity(String productImageUrl, MemberEntity memberEntity) {

        return ProductEntity.builder()
                .pid(this.pid)
                .ptitle(this.ptitle)
                .pcontent(this.pcontent)
                .productImageUrl(productImageUrl)
                .pdate(this.pdate)
                .plike(this.plike)
                .pprice(this.pprice)
                .ptype(this.ptype)
                .memberEntity(memberEntity)
                .build();

    }

}
