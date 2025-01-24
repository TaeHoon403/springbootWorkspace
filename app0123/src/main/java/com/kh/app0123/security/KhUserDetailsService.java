package com.kh.app0123.security;

import com.kh.app0123.member.MemberDao;
import com.kh.app0123.member.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KhUserDetailsService implements UserDetailsService {

    private final MemberDao dao;

    // authenticationManager 가 내부적으로 해당 메서드를 호출해서 로그인 성공여부를 확인한다
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MemberVo vo = dao.getMemberVoById(username);

        return new KhUserDetails(vo);
    }

}
