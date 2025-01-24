package com.kh.app0123.member;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @PostMapping("join")
    public String join(MemberVo vo){
        System.out.println("MemberController.join begin~~");
        service.join(vo);
        System.out.println("MemberController.join done~~");
        return "join success~";
    }

    @GetMapping("login")
    public void login(){

    }

}
