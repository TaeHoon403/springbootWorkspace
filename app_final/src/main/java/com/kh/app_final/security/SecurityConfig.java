package com.kh.app_final.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        
        // csrf 끄기
        httpSecurity.csrf(auth -> auth.disable());
        
        // 모든 요청 통과 시키기
        httpSecurity.authorizeHttpRequests(
                auth -> auth.anyRequest().permitAll()
        );
        
        // cors 처리
        httpSecurity.cors(
            corsConfig -> corsConfig.configurationSource(request -> {
                CorsConfiguration conf = new CorsConfiguration();
                conf.addAllowedOrigin("*");
                conf.addAllowedMethod("*");
                conf.addAllowedHeader("*");
                return conf;
            })
        );

        return httpSecurity.build();

    }//securityFilterChain

}//class


//new CorsConfigurationSource() {
//    @Override
//    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//
//        CorsConfiguration conf = new CorsConfiguration();
//        conf.addAllowedOrigin("http://localhost:3000");
//        conf.addAllowedHeader("*");
//        conf.addAllowedMethod("*");
//
//        return conf;
//    }
//}