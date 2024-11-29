package com.kh.app01.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    // 회원가입 (화면)
    @GetMapping("member/join")
    public String join(){
        return  "member/join";
    }

}
