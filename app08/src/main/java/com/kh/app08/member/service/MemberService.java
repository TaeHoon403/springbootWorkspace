package com.kh.app08.member.service;

import com.kh.app08.member.dao.MemberDao;
import com.kh.app08.member.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberDao dao;

    public int join(MemberVo vo) {
        return dao.join(vo);
    }

    public MemberVo login(MemberVo vo) {
        return dao.login(vo);
    }
}
