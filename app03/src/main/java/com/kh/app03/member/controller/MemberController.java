package com.kh.app03.member.controller;

import com.kh.app03.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    @Autowired
    private MemberMapper mapper;

    @GetMapping("member/join")
    @ResponseBody
    public String join() {
        System.out.println("회원가입 호출 됨~~");

        mapper.m01();

        return "join success~~";
    }

}
