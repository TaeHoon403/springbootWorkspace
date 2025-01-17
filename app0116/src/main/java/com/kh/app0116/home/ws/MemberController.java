package com.kh.app0116.home.ws;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @GetMapping("login")
    public String login(){
        return "login";
    }

    @PostMapping("login")
    public String login(String nick, HttpSession session){
        session.setAttribute("nick",nick);
        return "redirect:/home";
    }

    @GetMapping("check")
    public String check(HttpSession session){
        String nick = (String) session.getAttribute("nick");
        System.out.println("nick = " + nick);
        return "loginInfo : "+nick;
    }

}
