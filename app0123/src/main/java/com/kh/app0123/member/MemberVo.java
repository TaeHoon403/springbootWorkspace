package com.kh.app0123.member;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberVo {

    private String id;
    private String pwd;
    private String nick;
    private String role;

}
