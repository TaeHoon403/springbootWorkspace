package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/")
    public String m01(){
        return "root~~~~";
    }

    @GetMapping("/main")
    public String m02(){
        return "main~~~~";
    }

    @GetMapping("/hello")
    public String m03(){
        return "hello~~~~";
    }

}
