package com.kh.app_aws;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("KTH")
public class HelloController {

    @GetMapping("hello")
    public String m01(){
        return "hello~~~ kth~~~~~ server~~~~";
    }

    @GetMapping("bye")
    public String m02(){
        return "goodbye~~~ ";
    }

}
