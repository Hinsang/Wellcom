package com.Wellcom.controller;

import com.Wellcom.config.auth.PrincipalDetails;
import com.Wellcom.domain.dto.MemberDto;
import com.Wellcom.domain.dto.UpdateMemberDto;
import com.Wellcom.domain.entity.MemberEntity;
import com.Wellcom.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")  // front URL 페이지 경로와 매핑되는 경로를 다르게 설정해야 한다. (GET 요청일지라도 POST 경로와 달라야 한다. 몇 시간 헤맸다. ㅠㅠ)
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/signup")
    public boolean signup(@RequestBody MemberDto memberDto) {

        System.out.println("요청성공");
        return memberService.signup(memberDto);

    }

    @GetMapping("/loadUser")
    public MemberDto loadUser(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {

        if(principalDetails != null) {

            model.addAttribute("principal", principalDetails.getMemberEntity());

            MemberDto memberDto = principalDetails.getMemberEntity().toDto();
            System.out.println(memberDto);

            return memberDto;

        } else {

            return null;

        }

    }

    @PostMapping("/update")
    public boolean update(@RequestBody UpdateMemberDto updateMemberDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        return memberService.update(updateMemberDto, principalDetails);

    }

}
