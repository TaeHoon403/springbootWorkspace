package com.kh.app0123.member;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberDao {

    private final Map<String, MemberVo> map = new HashMap<>();

    public void join(MemberVo vo) {
        map.put(vo.getId(), vo);
        System.out.println("map ::: = " + map);
    }

    public MemberVo getMemberVoById(String username) {
        return map.get(username);
    }

}
