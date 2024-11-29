package com.kh.app.member.controller;

import com.kh.app.member.vo.MemberVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    @GetMapping("abc")
    @ResponseBody
    public String m01(MemberVo vo) {
        System.out.println("m01 called ~~~~~");

        System.out.println("vo = "+ vo);

        return "wkadhsek";
    }

}
