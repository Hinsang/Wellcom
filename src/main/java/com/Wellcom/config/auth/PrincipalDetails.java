package com.Wellcom.config.auth;

import com.Wellcom.domain.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class PrincipalDetails implements UserDetails {

    // 모든 Class는 UID를 가지고 있는데 Class의 내용이 변경되면 UID값 역시 같이 바뀌어 버립니다. 직렬화하여 통신하고 UID값으로 통신한 게 정상인지 확인하는데 그 값이 바뀌게 되면 다른 Class로 인식을 해버리게 됩니다.
    // 이를 방지하기 위해 고유값으로 미리 명시를 해주는 부분이 바로 "private static final long serialVersionUID" 이 부분이다.
    private static final long serialVersionUID = 1L;

    private MemberEntity memberEntity;

    @Autowired
    public PrincipalDetails(MemberEntity memberEntity) {
        this.memberEntity = memberEntity;
    }

    // SimpleGrantedAuthority로 객체를 생성해서 권하는 표현 (가장 일반적인 방식, 호환성이 훨씬 좋다)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_CUSTOM"));
        return authorities;
    }

    /*
    // 엔티티 안에 Getter 메서드를 사용할 필요가 없는 커스텀 고정 권한 방식
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collector = new ArrayList<>();
        collector.add(() -> {
            return "ROLE_CUSTOM"; // 사용자의 권한을 "ROLE_CUSTOM"으로 설정
        });
        return collector;
    }
    */

    /*
    // 1개를 초과하는 권한
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collector = new ArrayList<>(); // 접근 권한 복수 설정하기
        collector.add(() -> memberEntity.getRole());
        return collector;

    }
    */

    @Override
    public String getPassword() {
        return memberEntity.getPassword(); // 로그인 패스워드
    }

    @Override
    public String getUsername() {
        return memberEntity.getUsername(); // 로그인 아이디
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // 계정 만료 여부
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // 계정 잠김 여부
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 비밀번호 만료 여부
    }

    @Override
    public boolean isEnabled() {
        return true; // 계정 활성화 여부
    }

}
