package com.kh.app07.board.vo;

import lombok.Data;

@Data
public class BoardVo {

    private String no;
    private String title;
    private String content;
    private String writerNo;
    private String writerNick;
    private String hit;
    private String enrollDate;
    private String modifyDate;
    private String delYn;

}
