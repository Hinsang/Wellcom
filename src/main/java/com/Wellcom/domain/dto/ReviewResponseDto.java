package com.Wellcom.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDto {

    private int rid;
    private int stars;
    private String comment;
    private LocalDateTime date;
    private String name;

}
