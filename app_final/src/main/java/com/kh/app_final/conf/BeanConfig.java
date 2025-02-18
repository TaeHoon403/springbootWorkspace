package com.kh.app_final.conf;

import com.kh.app_final.jwt.JwtFilter;
import com.kh.app_final.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfig {

    // JwtFilter의 매개변수로 필요한 jwtUtil 생성
    private final JwtUtil jwtUtil;

    // 암호화 객체 Bean 생성(BCryptPasswordEncoder)
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }//bCryptPasswordEncoder
    
    /* // 로그인 여부 검증 filter 등록
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){

        FilterRegistrationBean filter =  new FilterRegistrationBean<>(new JwtFilter(jwtUtil)); // filter 생성
        filter.addUrlPatterns("*"); // 모든 url 에 대하여 filter 동작
        filter.setOrder(1); // 동작 순위 1순위
        return filter;

    }//filterRegistrationBean */
    
}//class
