package com.kh.app0131.encode;

import org.springframework.stereotype.Component;

@Component
public class KhEncoder {

    // 암호화
    public String encode(String rawData){

        String encryptData = rawData+"abc";
        return encryptData;

    }//encode

    // 암호 검증
    public boolean match(String rawData , String encryptData){

        // 일치 여부 검증
        return encryptData.equals(rawData+"abc");

    }//match

}//class
