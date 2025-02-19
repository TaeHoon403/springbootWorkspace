package com.kh.app_final.admin;

import lombok.Data;

@Data
public class AdminVo {

    private Long no;
    private String id;
    private String pwd;
    private String nick;
    private String enrollDate;
    private String modifyDate;
    private String delYn;

}
