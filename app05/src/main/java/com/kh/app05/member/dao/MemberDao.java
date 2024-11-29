package com.kh.app05.member.dao;

import com.kh.app05.member.mapper.MemberMapper;
import com.kh.app05.member.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberDao {

    private final MemberMapper mm;

    public int join(MemberVo vo) {
        return mm.join(vo);
    }

    public MemberVo login(MemberVo vo) {
        return mm.login(vo);
    }
}
