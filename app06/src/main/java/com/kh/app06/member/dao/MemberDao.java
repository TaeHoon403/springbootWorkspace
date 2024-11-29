package com.kh.app06.member.dao;

import com.kh.app06.member.mapper.MemberMapper;
import com.kh.app06.member.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberDao {

    private final MemberMapper mm;

    public int join(MemberVo vo) {
        return mm.join(vo);
    }

    public MemberVo login(MemberVo vo){
        return mm.login(vo);
    }


}
