package com.kh.app0131.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(auth ->auth.disable());
        http.authorizeHttpRequests(
//                x -> x.requestMatchers("/api/member/join").permitAll()
                x -> x.anyRequest().permitAll()
        );
        return  http.build();
    }//securityFilterChain

}//class
