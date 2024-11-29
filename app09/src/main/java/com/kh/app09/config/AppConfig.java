package com.kh.app09.config;

import com.kh.app09.filter.KhFilter;
import com.kh.app09.home.controller.HomeService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public HomeService homeService(){
        return new HomeService();
    }

    @Bean
    public FilterRegistrationBean m01(){

        // 필터 객체 생성
        FilterRegistrationBean x = new FilterRegistrationBean(new KhFilter());

        // 해당 필터의 URL 설정
        x.addUrlPatterns("/*");

        // 필터가 여러개일 경우 해당 필터의 동작 순번
        x.setOrder(1);

        return x;
    }

}
