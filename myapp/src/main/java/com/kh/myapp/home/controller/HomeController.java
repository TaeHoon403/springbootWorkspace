package com.kh.myapp.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("hello")
    public void m01(){

        System.out.println("메서드 호출댐~~~~");

    }

}
