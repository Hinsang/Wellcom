package com.Wellcom.domain.entity;

import com.Wellcom.domain.dto.ReviewDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "review")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rid;
    private int stars;
    private String comment;
    private LocalDateTime date;

    @JoinColumn(name = "mid")
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private MemberEntity mid;

    @JoinColumn(name = "pid")
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private ProductEntity pid;

    public ReviewDto toDto() {

        return ReviewDto.builder()
                .rid(this.rid)
                .stars(this.stars)
                .comment(this.comment)
                .date(this.date)
                .build();

    }

}
