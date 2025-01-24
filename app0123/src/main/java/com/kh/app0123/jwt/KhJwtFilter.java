package com.kh.app0123.jwt;


import com.kh.app0123.member.MemberVo;
import com.kh.app0123.security.KhUserDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class KhJwtFilter extends OncePerRequestFilter {

    private final KhJwtUtil khJwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //  토큰 정보 가져와서 검증 후 필요한 데이터 꺼내오기 (id,role)
        String auth =  request.getHeader("Authorization");

        // jwt 정보 없거나 bearer 로 시작하지 않으면 인증 통과 X
        if( auth == null || !auth.startsWith("Bearer ")){
            System.out.println("******* JWT 없음 *******");
            filterChain.doFilter(request,response);
            return;
        }

        String jwt = auth.split(" ")[1];
        System.out.println("jwt = " + jwt);
        String id = khJwtUtil.getId(jwt);
        String role = khJwtUtil.getRole(jwt);

        MemberVo vo =  new MemberVo();
        vo.setId(id);
        vo.setRole(role);
        
        // 유저정보 객체 생성
        KhUserDetails khUserDetails = new KhUserDetails(vo);
        
        // jwt 만료되었으면 인증 통과 X
        if(khJwtUtil.isExpired(jwt)){
            System.out.println("******* JWT 기간 만료 *******");
            filterChain.doFilter(request,response);
            return;
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(khUserDetails,null,khUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request,response);
    }

}
