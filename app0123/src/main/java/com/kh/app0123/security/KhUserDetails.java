package com.kh.app0123.security;

import com.kh.app0123.member.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class KhUserDetails implements UserDetails {

    private final MemberVo vo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> list = new ArrayList<>();
        list.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return vo.getRole();
            }
        });
        return list;
    }

    @Override
    public String getPassword() {
        return vo.getPwd();
    }

    @Override
    public String getUsername() {
        return vo.getId();
    }

}
