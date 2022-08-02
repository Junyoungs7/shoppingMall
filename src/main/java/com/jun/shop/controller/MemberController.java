package com.jun.shop.controller;

import com.jun.shop.dto.MemberFormDto;
import com.jun.shop.entity.Member;
import com.jun.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/new")
    public String memberForm(MemberFormDto memberFormDto ,Model model){
        model.addAttribute("memberFormDto", memberFormDto);
        return "member/joinForm";
    }

    @PostMapping("/new")
    public String newMember(@Valid MemberFormDto memberFormDto,
                            BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "member/joinForm";
        }

        try{
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        }catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/joinForm";
        }
//        Member member = Member.createMember(memberFormDto, passwordEncoder);
//        memberService.saveMember(member);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String memberLogin() {
        return "member/login";
    }

    // 로그인 실패
    @GetMapping("/login/fail")
    public String memberLoginFail(Model model) {
        model.addAttribute("loginFailMsg", "아이디 또는 비밀번호를 확인해주세요.");
        return "member/login";
    }
}
