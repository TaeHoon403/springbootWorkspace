package com.kh.app0121.member;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @PostMapping("join")
    public String join(MemberVo vo){
        service.join(vo);
        return "join success~~";
    }

    @GetMapping("login")
    public String login(MemberVo vo){
        MemberVo loginVo = service.login(vo);
        System.out.println("loginVo = " + loginVo);
        return "login~~";
    }


}
