package com.kh.app08.member.controller;

import com.kh.app08.member.service.MemberService;
import com.kh.app08.member.vo.MemberVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @GetMapping("join")
    public void join(){

    }

    @PostMapping("join")
    public String join(MemberVo vo, MultipartFile f) throws IOException {

        System.out.println("vo = " + vo);
        System.out.println("f = " + f);

        if(!f.isEmpty()){
            String OriginalFileName = f.getOriginalFilename();
            String ext = OriginalFileName.substring(OriginalFileName.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString() + ext;
            File target = new File("D:\\dev\\springbootWorkspace\\app08\\src\\main\\resources\\static\\img\\profile\\"+newFileName);

            f.transferTo(target);
            System.out.println("target = " + target);

            vo.setProfile("img\\profile\\"+newFileName);
        }

        int result = service.join(vo);

        if(result == 1){
            return "redirect:/home";
        }
        else {
            return "redirect:/error";
        }

    }

    @GetMapping("login")
    public void login(){}

    @PostMapping("login")
    public String login(MemberVo vo, HttpSession session){

        MemberVo loginInfo = service.login(vo);

        if(loginInfo != null){
            session.setAttribute("loginInfo", loginInfo);
            return "redirect:/home";
        }
        else{
            return "redirect:/error";
        }

    }

}
