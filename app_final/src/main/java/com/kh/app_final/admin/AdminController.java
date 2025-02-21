package com.kh.app_final.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // 관리자 로그인
    @PostMapping("login")
    public String login(@RequestBody AdminVo vo){
        try{
            return adminService.login(vo);
        }catch(Exception e){
            throw new IllegalStateException("[ADMIN-LOGIN] LOGIN FAIL ...");
        }
    }//login

}//class
