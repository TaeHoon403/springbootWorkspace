package com.kh.app_final.member;

import com.kh.app_final.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberMapper mapper;

    private final BCryptPasswordEncoder encoder;

    private final JwtUtil jwtUtil;

    // 회원 가입
    public int join(MemberVo vo) {

        // 비밀번호 암호화
        vo.setPwd(encoder.encode(vo.getPwd()));

        return mapper.join(vo);

    }//join

    // 로그인
    public String login(MemberVo vo) {

        // 로그인 정보로 회원정보 조회
        MemberVo dbVo = findUserById(vo.getId());
        System.out.println("dbVo = " + dbVo);

        // 로그인 정보 일치여부 확인
        boolean isMatch = encoder.matches(vo.getPwd(),dbVo.getPwd());
        if (!isMatch){
            throw new IllegalStateException("로그인 실패");
        }
        
        // 토큰 반환
        return jwtUtil.createJwtToken(dbVo.getNo(),dbVo.getId(),dbVo.getNick(),"user");

    }//login

    // 회원 정보 조회 by ID
    public MemberVo findUserById(String id){
        return mapper.findUserById(id);
    }//findUserById

}//class
