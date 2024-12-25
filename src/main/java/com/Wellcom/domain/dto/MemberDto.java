package com.Wellcom.domain.dto;

import com.Wellcom.domain.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MemberDto {

    private int mid;
    private String name;
    private String username;
    private String password;
    private String role;

    private LocalDateTime createDate;

    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .mid(this.mid)
                .name(this.name)
                .username(this.username)
                .password(this.password)
                .role(this.role)
                .build();
    }

}
