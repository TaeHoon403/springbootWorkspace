package com.kh.app05.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    @RequestMapping(value = "home" , method = RequestMethod.GET)
    public String home(){
        // home.jsp 로 포워딩
        return "home";
    }


}
