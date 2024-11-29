package com.kh.app09.home.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("home")
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final HomeService hs;

    @GetMapping
    @ResponseBody
    public String home(){
        log.info("(info) 홈페이지 보기 요청 발생");
        log.trace("(trace) 홈페이지 보기 요청 발생");
        log.debug("(debug) 홈페이지 보기 요청 발생");
        log.warn("(warn) 홈페이지 보기 요청 발생");
        log.error("(error) 홈페이지 보기 요청 발생");

        hs.home();

        return "<h1>home</h1>";
    }

}
