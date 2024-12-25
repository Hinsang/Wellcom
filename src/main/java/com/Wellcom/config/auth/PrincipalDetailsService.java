package com.Wellcom.config.auth;

import com.Wellcom.domain.entity.MemberEntity;
import com.Wellcom.domain.entity.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("username : " + username);
        System.out.println("=============================");

        MemberEntity memberEntity = memberRepository.findByUsername(username);

        if(memberEntity == null) {
            // throw new UsernameNotFoundException("User not found with username: " + username);
            return null;
        } else {
            System.out.println(memberEntity + "정보 들어왔어요!!");
            return new PrincipalDetails(memberEntity); // PrincipalDetails에 유저 정보 넣기
        }

    }

}
