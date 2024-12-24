package com.kh.snack;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SnackVo {

    /*
     *   long 이 아닌 객체형식인 Long 타입을 쓴 이유는 null 처리 때문
     *   long의 경우 null 이 들어오면 기본값인 0으로 표시 되지만
     *   객체인 Long의 경우 null로 표시된다.
     *   같은 이유로 price 도 int 가 아닌 Integer 사용
     */
    private Long no;
    private String name;
    private Integer price;
    private String brand;
    private String expirationDate;

}
