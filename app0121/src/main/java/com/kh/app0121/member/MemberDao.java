package com.kh.app0121.member;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberDao {

    private final Map<Long, MemberVo> map = new HashMap<>();
    private Long seq = 0L;

    public void join(MemberVo vo) {
        vo.setNo(seq++);
        map.put(seq,vo);
    }

    public MemberVo login(MemberVo vo) {
       Collection<MemberVo> collection = map.values();
       for (MemberVo memberVo : collection) {
           if(memberVo.getId().equals(vo.getId())){
                return memberVo;
           }
       }
       return null;
    }

}
