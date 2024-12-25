package com.Wellcom.domain.dto;

import com.Wellcom.domain.entity.SearchEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchDto {

    String text;

    public SearchEntity toEntity() {
        return SearchEntity.builder()
                .text(this.text)
                .build();
    }

}
