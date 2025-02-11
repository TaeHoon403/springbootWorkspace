package com.kh.app_final.member;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/member")
public class MemberController {

    private final MemberService service;

    // 회원가입
    @PostMapping("join")
    public String join(@RequestBody MemberVo vo) {

        try {
            // service 호출
            service.join(vo);
        }catch (Exception e){
            throw new IllegalStateException("[MEMBER-Join] 회원가입 실패");
        }

        // 결과 반환
        return "join success~~~~";
        
    }//join

    // 로그인
    @PostMapping("login")
    public String login(@RequestBody MemberVo vo){
        System.out.println("vo = " + vo);
        // service 호출
        try {
            return service.login(vo);
        }catch (Exception e){
            throw new IllegalStateException("[MEMBER-LOGIN] 로그인 실패");
        }

    }//login

}//class
