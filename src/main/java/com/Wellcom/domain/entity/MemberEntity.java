package com.Wellcom.domain.entity;

import com.Wellcom.domain.dto.MemberDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mid;
    private String name;
    private String username;
    private String password;
    private String role;
    private LocalDateTime createDate;
    @OneToMany(mappedBy = "memberEntity", fetch = FetchType.EAGER) // 기본 설정값으로 연관된 엔티티를 즉시 조인해서 로드
    //@JsonIgnoreProperties({"memberEntity"})
    @JsonManagedReference
    private List<ProductEntity> productEntityList;

    @OneToMany(mappedBy = "mid")
    @JsonManagedReference
    private List<ReviewEntity> reviewEntityList;

    @PrePersist
    public void createDate() {
        this.createDate = LocalDateTime.now();
    }

    public MemberDto toDto() {
        return MemberDto.builder()
                .mid(this.mid)
                .name(this.name)
                .username(this.username)
                .password(this.password)
                .role(this.role)
                .createDate(this.createDate)
                .build();
    }

    @Override
    public String toString() {

        return "MemberEntity {" +
                "mid = " + mid +
                ", name = " + name +
                ", username = " + username +
                ", role = " + role +
                ", createDate = " + createDate +
                "}";

    }

}
