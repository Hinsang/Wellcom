package com.Wellcom.domain.entity;

import com.Wellcom.domain.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    private String ptitle;
    private String pcontent;
    private String productImageUrl;
    private int pprice;
    private int plike;
    private String ptype;
    private LocalDateTime pdate;

    //@JsonIgnoreProperties({"productEntityList"})
    @JoinColumn(name = "mid")
    @ManyToOne(fetch = FetchType.LAZY) // 자식 엔티티가 필요한 경우에만 로드, 추가 쿼리가 필요
    @JsonBackReference
    private MemberEntity memberEntity;

    @OneToMany(mappedBy = "pid")
    @JsonManagedReference
    private List<ReviewEntity> reviewEntityList;

    @PrePersist
    public void pdate() {
        this.pdate = LocalDateTime.now();
    }

    public ProductDto toDto() {

        return ProductDto.builder()
                .pid(this.pid)
                .ptitle(this.ptitle)
                .pcontent(this.pcontent)
                .pdate(this.pdate)
                .plike(this.plike)
                .pprice(this.pprice)
                .ptype(this.ptype)
                .productImageUrl(this.productImageUrl)
                .build();

    }

    @Override
    public String toString() {

        return "ProductEntity {" +
                "pid = " + pid +
                ", ptitle = " + ptitle +
                ", pcontent = " + pcontent +
                ", pdate = " + pdate +
                ", pprice = " + pprice +
                ", ptype = " + ptype +
                "}";

    }

}
