package com.kh.SEMI.admin.controller;

import com.kh.SEMI.admin.service.AdminService;
import com.kh.SEMI.admin.vo.AdminVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final AdminService service;
    
    // 관리자 로그인 화면 이동
    @GetMapping("login")
    public void login(){}
    
    // 관리자 로그인 처리
    @PostMapping("login")
    public String login(AdminVo vo, HttpSession session){
        
        // service 호출
        AdminVo loginAdminVo = service.login(vo);
        
        // 예외처리
        if (loginAdminVo == null){
            throw new IllegalStateException("관리자 로그인 중 오류 발생");
        }
        
        // 결과 전달
        session.setAttribute("loginAdminVo", loginAdminVo);
        session.setAttribute("alertMsg", "관리자 로그인 성공!");
        
        // 홈 화면 이동
        return "redirect:/home";
        
    }//login

}//class
