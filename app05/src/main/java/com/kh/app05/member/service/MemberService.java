package com.kh.app05.member.service;

import com.kh.app05.member.dao.MemberDao;
import com.kh.app05.member.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberDao md;

    public int join(MemberVo vo){
        return md.join(vo);
    }

    public MemberVo login(MemberVo vo) {
        return md.login(vo);
    }
}

