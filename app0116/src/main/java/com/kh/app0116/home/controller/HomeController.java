package com.kh.app0116.home.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("home")
public class HomeController {

    @GetMapping
    public String home(HttpSession session){
        String nick = (String) session.getAttribute("nick");
        if(nick == null || nick.length() <1){
            return "redirect:/login";
        }
        return "home";
    }

}
