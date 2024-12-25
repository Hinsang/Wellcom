package com.Wellcom.service;

import com.Wellcom.config.auth.PrincipalDetails;
import com.Wellcom.domain.dto.MemberDto;
import com.Wellcom.domain.dto.UpdateMemberDto;
import com.Wellcom.domain.entity.MemberEntity;
import com.Wellcom.domain.entity.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    private HttpServletRequest request;

    @Transactional
    public boolean signup(MemberDto memberDto) {

        MemberEntity memberEntity = memberDto.toEntity();

        // 변경 전 패스워드
        String rawPassword = memberEntity.getPassword();
        // Bcrypt 암호화 패스워드
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);

        memberEntity.setPassword(encPassword);

        MemberEntity memberEntityResult = memberRepository.save(memberEntity);
        if(memberEntityResult.getMid() != 0) {
            return true;
        }
        return false;

    }

    @Transactional
    public boolean update(UpdateMemberDto updateMemberDto, PrincipalDetails principalDetails) {

        MemberEntity memberEntity = memberRepository.findByUsername(principalDetails.getUsername());

        if (updateMemberDto.getPassword() != null && !updateMemberDto.getPassword().isEmpty()) {
            String encodedPassword = bCryptPasswordEncoder.encode(updateMemberDto.getPassword());
            memberEntity.setPassword(encodedPassword);
        } else {
            return false;
        }

        memberRepository.save(memberEntity);

        return true;

    }

}
