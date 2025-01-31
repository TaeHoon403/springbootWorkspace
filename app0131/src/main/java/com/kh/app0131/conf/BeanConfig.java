package com.kh.app0131.conf;

import com.kh.app0131.filter.TokenCheckFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanConfig {

    // BCryptPassword 인코더 bean 등록
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }//bCryptPasswordEncoder
    
    // token 검증 filter bean 등록
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){

        FilterRegistrationBean filter =  new FilterRegistrationBean<>(new TokenCheckFilter(bCryptPasswordEncoder()));

        // filter 동작할 url 설정
        filter.addUrlPatterns("/*");

        // 필터 반환
        return filter;

    }//filterRegistrationBean

}//class
