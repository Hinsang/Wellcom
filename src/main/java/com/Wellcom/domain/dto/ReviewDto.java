package com.Wellcom.domain.dto;

import com.Wellcom.domain.entity.MemberEntity;
import com.Wellcom.domain.entity.ProductEntity;
import com.Wellcom.domain.entity.ReviewEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDto {

    int rid;
    int stars;
    String comment;
    LocalDateTime date;
    int mid;
    int pid;

    public ReviewEntity toEntity(MemberEntity memberEntity, ProductEntity productEntity) {

        return ReviewEntity.builder()
                .rid(this.rid)
                .stars(this.stars)
                .comment(this.comment)
                .date(this.date)
                .mid(memberEntity)
                .pid(productEntity)
                .build();

    }

}
