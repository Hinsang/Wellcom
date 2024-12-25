package com.Wellcom.domain.entity;

import com.Wellcom.domain.dto.SearchDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "search")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String text;

    public SearchDto toDto() {
        return SearchDto.builder()
                .text(this.text)
                .build();
    }

}
