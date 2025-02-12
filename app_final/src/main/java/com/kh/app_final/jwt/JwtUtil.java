package com.kh.app_final.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    // 서명을 위한 암호키 객체 생성
    private SecretKey secretKey;
    public JwtUtil(@Value("${kh.jwt.secret}") String secret) {
        byte[] bytes = secret.getBytes(StandardCharsets.UTF_8);
        this.secretKey = new SecretKeySpec(bytes,"HmacSHA256");
    }

    // 토큰 생성
    public String createJwtToken(Long no,String  id, String nick, String role){

        long expired = 1000*60*15;

        return Jwts.builder()
                .claim("no",no)
                .claim("id",id)
                .claim("nick",nick)
                .claim("role",role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+expired))
                .signWith(secretKey)
                .compact()
                ;

    }//createJwtToken

}//class
