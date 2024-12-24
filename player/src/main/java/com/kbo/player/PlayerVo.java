package com.kbo.player;

import lombok.Data;

@Data
public class PlayerVo {

    private Long no;
    private String name;
    private String team;
    private String position;
    private int backNumber;
    private String debutDate;
    private String retiredYn;

}
