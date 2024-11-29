package com.kh.app05.member.controller;

import com.kh.app05.member.service.MemberService;
import com.kh.app05.member.vo.MemberVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService ms;

    // 회원가입 화면 출력
    @GetMapping("join")
    public String join(){
        return "member/join";
    }

    // 회원가입 처리
    @PostMapping("join")
    public String join(MemberVo vo){

        int result = ms.join(vo);

        if(result == 1){
            return "redirect:/home";
        }
        else{
            return "redirect:/error";
        }
    }

    // 로그인 화면 출력
    @GetMapping("login")
    public String login(){
        return "member/login";
    }

    // 로그인 처리
    @PostMapping("login")
    public String login(MemberVo vo, HttpSession session){

        MemberVo loginInfo = ms.login(vo);

        if(loginInfo != null){
            session.setAttribute("loginInfo",loginInfo);
            return "redirect:/home";
        }
        else{
            return "redirect:/error";
        }

    }

}
