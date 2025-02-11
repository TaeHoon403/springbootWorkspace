package com.kh.app0131.member;

import com.kh.app0131.encode.KhEncoder;
import com.kh.app0131.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberMapper mapper;
//    private final KhEncoder encoder;
    private final BCryptPasswordEncoder encoder;

    private final JwtUtil jwtUtil;

    // 회원가입
    public int join(MemberVo vo) {
        // 입력 된 비밀번호 정보 암호화
        vo.setPwd(encoder.encode(vo.getPwd()));
        
        // mapper 호출
        return mapper.join(vo);

    }//join

    // 로그인
    public String login(MemberVo vo) {
        
        // DB 에서 전달받은 아이디와 일치하는 계정정보 수집
        MemberVo dbVo = mapper.findById(vo.getId());

        // 일치여부 확인
        if(!encoder.matches(vo.getPwd(), dbVo.getPwd())){
            throw new IllegalStateException("로그인 실패..");
        }

        // 토큰 발행
        return jwtUtil.createToken( dbVo.getNo(), dbVo.getNick(), (1000*60*15));

        /*String secretKey = "dlrjsskaksdksmsrkqt"; // dlrjsskaksdksmsrkqt = 이건나만아는값
        String encodeDate = encoder.encode(dbVo.getNo()+dbVo.getNick()+secretKey);
        String token = "kh "+dbVo.getNo()+"!@#"+dbVo.getNick()+"!@#"+encodeDate;
        return token;*/

    }//login

}//class
