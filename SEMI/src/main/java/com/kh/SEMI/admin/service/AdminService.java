package com.kh.SEMI.admin.service;

import com.kh.SEMI.admin.mapper.AdminMapper;
import com.kh.SEMI.admin.vo.AdminVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AdminService {

    private final AdminMapper mapper;

    // 관리자 로그인
    public AdminVo login(AdminVo vo) {
        return mapper.getAdminVoByIdAndPwd(vo);
    }//login

}//class
