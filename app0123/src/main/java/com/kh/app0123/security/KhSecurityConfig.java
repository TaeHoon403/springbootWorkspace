package com.kh.app0123.security;

import com.kh.app0123.jwt.KhJwtFilter;
import com.kh.app0123.jwt.KhJwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class KhSecurityConfig {

    private final AuthenticationConfiguration conf;

    private final KhJwtUtil khJwtUtil;

    // 암호화 bean
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder (){
        return new BCryptPasswordEncoder();
    }

    // AuthenticationManager 객체 생성 bean
    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration conf) throws Exception {
        return conf.getAuthenticationManager();
    }

    // security 의 filter 설정
    @Bean
    public SecurityFilterChain m01(HttpSecurity http) throws Exception {

        http.csrf(
                /*
                 *   csrf 의 경우 Customizer 라는 매개변수가 필요
                 *   해당 객체의 경우 함수 하나만 상속받는 인터페이스다
                 *   그래서 해당 인터페이스 객체를 전달하지 않고 함수 자체를 전달해줄 수 있다
                 *   전달 방식은 js 에서 화살표 함수를 만들어서 전달하던 방식처럼 하면 된다
                 *   (x)->x.disable()
                 *   클래스::함수 => 클래스의 함수를 전달한다(메서드 참조)
                 *   csrf란? 사용자가 자신의 의지와는 무관하게 공격자가 의도한 행위를 특정 사이트 접속 시 발생시키는 공격
                 */
                AbstractHttpConfigurer::disable
        );// csrf의 보안 항목 중 해당 기능을 쓰지 않겠다
        http.formLogin(AbstractHttpConfigurer::disable); // formLogin 화면 기능을 쓰지 않겠다
        http.httpBasic(AbstractHttpConfigurer::disable); // 기본적인 로그인 방식 기능을 사용하지 않겠다 (우린 토큰 방식 사용 예정)
        http.sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) ); // session을 사용하지 않겠다
        http.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/home","/join").permitAll() // 누구나 접근 가능
                        .requestMatchers("/admin/**").hasRole("ADMIN") // ADMIN 이라는 권한이 있어야 접근 가능
                        .anyRequest().authenticated()
        );// 접속 경로에 대한 인증 허용 여부 설정


        // 기본 로그인 필터를 내가 만든 로그인 필터로 교체
        http.addFilterAt(new KhLoginFilter(authenticationManager(conf),khJwtUtil), UsernamePasswordAuthenticationFilter.class);

        // 기본 인증 필터를 내가 만든 인증 필터로 교체
        http.addFilterBefore(new KhJwtFilter(khJwtUtil), KhLoginFilter.class);

        // security 에 cors 적용
        http.cors(
                corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

                        CorsConfiguration conf = new CorsConfiguration();
                        conf.setAllowedOrigins(Collections.singletonList("*")); //http://localhost:3000
                        conf.setAllowedMethods(Collections.singletonList("*"));
                        conf.setAllowCredentials(true);
                        conf.setAllowedHeaders(Collections.singletonList("*"));
                        conf.setMaxAge(1000L * 60 * 60);
                        conf.setExposedHeaders(Collections.singletonList("Authorization"));

                        return conf;
                    }
                })
        );

        return http.build();

    }

}
