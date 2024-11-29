package com.kh.app06.member.service;

import com.kh.app06.member.dao.MemberDao;
import com.kh.app06.member.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberDao md;

    public int join(MemberVo vo) {
        return md.join(vo);
    }

    public MemberVo login(MemberVo vo){
        return md.login(vo);
    }

}
