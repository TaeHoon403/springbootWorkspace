package com.kh.app0131.conf;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

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

        http.cors(
                corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                        CorsConfiguration conf = new CorsConfiguration();
                        conf.addAllowedOrigin("http://localhost:3000");
                        conf.addAllowedHeader("*");
                        conf.addAllowedMethod("*");

                        return conf;
                    }
                })
        );
        return  http.build();
    }//securityFilterChain

}//class

