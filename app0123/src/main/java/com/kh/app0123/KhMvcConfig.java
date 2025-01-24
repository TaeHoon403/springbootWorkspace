package com.kh.app0123;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class KhMvcConfig implements WebMvcConfigurer {

    // 모든 컨트롤러에 crosorign 설정을 한번에 처리
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedOriginPatterns("http://localhost:3000")
                ;
    }

}//class
