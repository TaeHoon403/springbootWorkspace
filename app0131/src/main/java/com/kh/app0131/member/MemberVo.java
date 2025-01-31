package com.kh.app0131.member;

import lombok.Data;

@Data
public class MemberVo {

    private Long no;
    private String id;
    private String pwd;
    private String nick;
    private String enrollDate;
    private String modifyDate;
    private String delYn;

}
