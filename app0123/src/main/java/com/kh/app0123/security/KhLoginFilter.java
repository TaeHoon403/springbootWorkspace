package com.kh.app0123.security;

import com.kh.app0123.jwt.KhJwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

// security 에서 기본으로 제공하는 login filter 를 우리가 만든 filter 로 대신한다
@RequiredArgsConstructor
public class KhLoginFilter extends UsernamePasswordAuthenticationFilter {

    // 인증매니저 객체(Authentication = 인증에 관련 기능)
    private final AuthenticationManager authenticationManager;
    
    // 우리가 만든 jwt 생성해주는 객체
    private final KhJwtUtil khJwtUtil;
    
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        return super.attemptAuthentication(request, response);
        System.out.println("################ KHLoginFilter start~~ ##############");

        // 로그인 정보에서 id,pwd 가져오기, 아래 코드는 security 내부적으로 존재하는 계정정보 수집 메서드
//        String id = request.getParameter("");
//        String pwd = request.getParameter("pwd");
        String id = obtainUsername(request); // 단 가져오는 키 값이 username 으로 고정되어 있음
        String pwd = obtainPassword(request); // 단 가져오는 키 값이 password 으로 고정되어 있음
        
        // 가져온 계정 정보로 토큰 생성
        // security 에선 로그인 정보를 토큰 형식으로 넘긴다(그냥 사용 방식)
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(id,pwd,null);

        // 가져온 토큰 정보로 로그인 성공여부 확인
        return authenticationManager.authenticate(token);

    }//attemptAuthentication

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter("id");
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return request.getParameter("pwd");
    }
    
    // 로그인 성공 시 동작 내용
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        super.successfulAuthentication(request, response, chain, authResult);
        System.out.println("#################### 로그인 성공~!!!! ####################");

        // 인증 정보 가져오기
        KhUserDetails khUserDetails = (KhUserDetails) authResult.getPrincipal();
        String id = khUserDetails.getUsername();
        String role = khUserDetails.getAuthorities().iterator().next().getAuthority();
        long expMs = 1000*60*15;

        // JWT (토큰)  발급
        String jwtToken = khJwtUtil.createJwt(id,role,expMs);
        System.out.println("jwtToken = " + jwtToken);
        
        // 발급된 토큰 헤더로 전달
        response.addHeader("Authorization","Bearer "+jwtToken);

        // 발급된 토큰 쿠키로 전달
        Cookie cookie = new Cookie("jwt",jwtToken);
        cookie.setMaxAge((int)expMs);
        cookie.setPath("/");
        cookie.setHttpOnly(true); // js 등을 이용한 악성스크립트 공격을 대비해 http 통신엥서만 사용 가능하도록 설정
        response.addCookie(cookie);

    }

    // 로그인 실패 시 동작 내용
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//        super.unsuccessfulAuthentication(request, response, failed);
        System.out.println("#################### 로그인 실패...... ####################");
        response.setStatus(401);
    }

}//class
