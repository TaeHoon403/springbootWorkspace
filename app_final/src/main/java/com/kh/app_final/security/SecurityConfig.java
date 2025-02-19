package com.kh.app_final.security;

import com.kh.app_final.jwt.JwtFilter;
import com.kh.app_final.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        
        // csrf 끄기
        httpSecurity.csrf(auth -> auth.disable());
        
        // 모든 요청 통과 시키기
        httpSecurity.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/","/home","api/member/join","api/member/login").permitAll() // 해당 경로의 경우 인증 없이 통과
                        .requestMatchers("api/gallery/list","api/gallery/detail/*").permitAll() // 해당 경로의 경우 인증 없이 통과
                        .requestMatchers("/api/admin/login").permitAll() // 해당 경로의 경우 인증 없이 통과
                        .requestMatchers("/admin/*").hasRole("ADMIN") // admin 관련 요청은 ADMIN 권한을 가져야 통과
                        .anyRequest().authenticated() // 그 외 모든 요청은 인증 되어야 통과
        );

        // filter 추가 (기존 UsernamePasswordAuthenticationFilter 를 내가 만든 필터로 변경)
        httpSecurity.addFilterAt(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

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