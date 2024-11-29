package com.kh.SEMI.notice.vo;

import lombok.Data;

@Data
public class NoticeVo {

    private String no;
    private String title;
    private String content;
    private String writerNo;
    private String writerNick;
    private String hit;
    private String createDate;
    private String delYn;

}
