package com.kh.app0121.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberDao dao;
    private final BCryptPasswordEncoder encoder;

    public void join(MemberVo vo) {
        String str = encoder.encode(vo.getPwd());
        System.out.println("str = " + str);
        vo.setPwd(str);
        dao.join(vo);
    }

    public MemberVo login(MemberVo vo) {
        MemberVo dbVo = dao.login(vo);
        System.out.println("dbVo = " + dbVo);
        boolean result = encoder.matches(vo.getPwd(),dbVo.getPwd());
        if (result){
            return dbVo;
        }
        else {
            return null;
        }
    }

}
