package com.kh.app06.member.controller;

import com.kh.app06.member.service.MemberService;
import com.kh.app06.member.vo.MemberVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("member")
public class MemberController {

    private final MemberService ms;

    // 로그인 처리
    @PostMapping("login")
    public String login(MemberVo vo, HttpSession session){

        MemberVo loginInfo = ms.login(vo);

        if(loginInfo != null){
            session.setAttribute("loginInfo", loginInfo);
            System.out.println("loginInfo = " + loginInfo);
            return "redirect:/home";
        }
        else{
            return "redirect:/error";
        }

    }

    // 회원가입 처리
    @PostMapping("join")
    public String join(MemberVo vo){

        int result = ms.join(vo);

        if(result == 1){
            return "redirect:/home";
        }
        else {
            return "redirect:/error";
        }

    }
}
