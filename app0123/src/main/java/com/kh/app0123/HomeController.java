package com.kh.app0123;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("home")
    public String home(){
        UserDetails loginInfo = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String nowLogin = "(now login : "+loginInfo.getUsername()+" - "+loginInfo.getAuthorities().iterator().next().getAuthority()+")";

        return "home page~~~~ "+nowLogin;
    }

    @GetMapping("board")
    public String board(){
        UserDetails loginInfo = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String nowLogin = "(now login : "+loginInfo.getUsername()+" - "+loginInfo.getAuthorities().iterator().next().getAuthority()+")";

        return "board page~~~~~(authenticated) "+nowLogin;
    }

    @GetMapping("admin")
    public String admin(){
        UserDetails loginInfo = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String nowLogin = "(now login : "+loginInfo.getUsername()+" - "+loginInfo.getAuthorities().iterator().next().getAuthority()+")";

        return "admin page ~~~~~~~~ (authorized / role=admin) "+nowLogin;
    }

}
