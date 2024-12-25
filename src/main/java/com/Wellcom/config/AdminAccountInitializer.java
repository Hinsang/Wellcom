package com.Wellcom.config;

import com.Wellcom.domain.entity.MemberEntity;
import com.Wellcom.domain.entity.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminAccountInitializer implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AdminAccountInitializer(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 프로젝트를 시작할 때 admin 계정이 없으면 추가하기
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        // 애플리케이션 시작 후 admin 계정이 없으면 생성
        if (!memberRepository.existsByUsername("admin")) {
            MemberEntity adminUser = new MemberEntity();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin")); // 비밀번호는 해싱하여 저장
            adminUser.setName("admin");
            adminUser.setRole("ADMIN_USER");
            memberRepository.save(adminUser);
        }

    }

}
