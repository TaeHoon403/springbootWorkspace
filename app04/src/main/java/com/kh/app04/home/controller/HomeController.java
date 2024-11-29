package com.kh.app04.home.controller;

import com.kh.app04.home.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private HomeService service;

    @GetMapping("home")
    public String home(){
        System.out.println("home controller 호출 됨~~~");

        // 서비스 호출
        service.home();

        return "home";
    }

}
