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

    public String login(AdminVo vo) {
        AdminVo dbVo = findAdminById(vo.getId());
        boolean isMatch = encoder.matches(vo.getPwd(), dbVo.getPwd());
        if(!isMatch){
            throw new IllegalStateException("ADMIN 로그인 실패");
        }

        return jwtUtil.createJwtToken(dbVo.getNo(), dbVo.getId() , dbVo.getNick() , "ROLE_ADMIN");
    }

    public AdminVo findAdminById(String id){
        return adminMapper.findAdminById(id);
    }

}//class
