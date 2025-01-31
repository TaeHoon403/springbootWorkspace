package com.kh.app0131.filter;

import com.kh.app0131.member.MemberVo;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;

@RequiredArgsConstructor
public class TokenCheckFilter implements Filter {

    private final BCryptPasswordEncoder encoder;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Token 검증 시작!!");

        // token 검증
        HttpServletRequest req = (HttpServletRequest) servletRequest;

        // 로그인 , 회원가입은 해당 필터 거치지 않고 넘어간다
        if(req.getRequestURI().contains("login") || req.getRequestURI().contains("join")){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        // 1. token 이라는 key 를 가진 데이터 꺼내기
//        String token = req.getParameter("token"); // 기존에 body 에서 꺼내올때
        String token = req.getHeader("token");
        if(token == null){
            throw new  IllegalStateException("토큰 확인 실패");
        }

        // 2. kh 라는 문자열로 시작하는지 체크
        boolean isKhToken = token.startsWith("kh");
        if(!isKhToken){
            throw new  IllegalStateException("kh 토큰 인증 실패");
        }

        // 3. ' !@# ' 구분자를 통해 no, nick 값이 있는지 체크
        if (!token.contains("!@#")){
            throw new  IllegalStateException("토큰 데이터 오류");
        }
        String[] tokenArr = token.split("!@#");

        String no = tokenArr[0].split(" ")[1];
        String nick = tokenArr[1];
        String encodeDate = tokenArr[2];
        if(no.length() < 1 || nick.length() < 1){
            throw new  IllegalStateException("토큰 데이터 오류2");
        }
        
        // 검증 성공한 데이터 저장
        System.out.println("Token 검증 성공!!");
        System.out.println("no = " + no);
        System.out.println("nick = " + nick);
        MemberVo vo =  new MemberVo();
        vo.setNo(Long.valueOf(no));
        vo.setNick(nick);

        // 유효성 검증
        boolean isMatch = encoder.matches(no + nick + "dlrjsskaksdksmsrkqt",encodeDate);
        if(!isMatch){
            throw new IllegalStateException("토큰 유효성 검사 실패");
        }

        // 임시 세션 생성
        HttpSession session = req.getSession();
        session.setAttribute("loginMemberVo", vo);

        // 토큰 인증 성공 시 다음 작업 진행
        filterChain.doFilter(servletRequest,servletResponse);
        session.invalidate(); // 다음 작업 끝나면 세션 만료 처리

    }//doFilter

}//class
