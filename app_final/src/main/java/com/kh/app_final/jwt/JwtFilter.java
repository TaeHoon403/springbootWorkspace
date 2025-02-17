package com.kh.app_final.jwt;

import com.kh.app_final.member.MemberVo;
import com.kh.app_final.security.KhUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    // 로그인 여부 검증
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        
        // 토큰 가져오기
        String token = request.getHeader("token");
        if(token == null || token.length() < 1){
            log.warn("token is null");
            filterChain.doFilter(request,response);
            return;
        }

        // 1. 토큰 만료 여부 검증
        if(jwtUtil.isExpired(token)){
            log.warn("token is expired");
            return;
        }

        // 토큰에서 데이터 꺼내기 - no
        Long no = jwtUtil.getNo(token);
        // 토큰에서 데이터 꺼내기 - id
        String id = jwtUtil.getId(token);
    
        // 꺼낸 데이터 객체로 저장
        MemberVo vo = new MemberVo();
        vo.setId(id);
        vo.setNo(no);
        
        /* // session 에 데이터 저장
        HttpSession session = request.getSession();
        session.setAttribute("loginMemberVo",vo); */

        // JWT 를 기반으로 인증 처리
        KhUserDetails khUserDetails = new KhUserDetails(vo,"USER");
        Authentication authToken = new UsernamePasswordAuthenticationToken(khUserDetails,null,khUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        // 검증 후 다음 동작으로 연결
        filterChain.doFilter(request,response);

    }//doFilterInternal

}//class
