package com.kh.app_final.admin;

import com.kh.app_final.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {

    private final AdminMapper adminMapper;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    // 관리자 로그인
    public String login(AdminVo vo) {
        AdminVo dbVo = findAdminById(vo.getId());
        boolean isMatch = encoder.matches(vo.getPwd(), dbVo.getPwd());
        if(!isMatch){
            throw new IllegalStateException("ADMIN 로그인 실패");
        }
        // 계정정보 인증 되면 인증 된 정보를 token 만들어 반환한다
        // 이때 role 정보도 추가해주는데 관리자 로그인이라 'ROLE_ADMIN'을 추가해준다
        return jwtUtil.createJwtToken(dbVo.getNo(), dbVo.getId() , dbVo.getNick() , "ROLE_ADMIN");
    }//login

    // 관리자 정보 수집
    public AdminVo findAdminById(String id){
        return adminMapper.findAdminById(id);
    }//findAdminById

}//class
