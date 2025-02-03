package com.kh.app0131.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin
@RequestMapping("api/member")
public class MemberController {

    private final MemberService service;

    // 회원가입
    @PostMapping("join")
    public String join(@RequestBody MemberVo vo){

        int result = service.join(vo);
        log.info("result ::: {}",result);

        if (result == 1){
            return "join success~~";
        }
        return "join fail...";

    }//join

    // 로그인
    @PostMapping("login")
    public String login(@RequestBody MemberVo vo){

        String token = service.login(vo);

        return token;

    }//login

}//class
