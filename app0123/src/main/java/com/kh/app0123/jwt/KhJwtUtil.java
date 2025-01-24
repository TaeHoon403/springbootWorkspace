package com.kh.app0123.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class KhJwtUtil {

    private final SecretKey secretKey;

    public KhJwtUtil(@Value("${kh.jwt.secret}") String str) {
        // 직접 secretkey 만들어서 사용
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        String algorithm = "HmacSHA256";
        this.secretKey = new SecretKeySpec(bytes,algorithm);
    }

    // JWT 생성
    public String createJwt(String id, String role, long expirationMs){

        return Jwts.builder()
                // 헤더 (토큰타입, 서명에 사용한 알고리즘 etc)
                    // Jwts 라이브러리에서 자동으로 생성
                // payload(로그인 정보 등의 데이터)
                .claim("id",id) // claim : payload 안에 존재하는 하나의 데이터
                .claim("role",role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+expirationMs))
                // 시그니처(헤더+페이로드를 암호화)
                .signWith(secretKey)
                // 생성
                .compact()
        ;

    }
    
    // JWT 에서 아이디 정보 추출
    public String getId(String jwt){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwt).getPayload().get("id",String.class);
    }

    // JWT 에서 토큰 기간만료 여부 확인
    public String getRole(String jwt){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwt).getPayload().get("role",String.class);
    }

    public boolean isExpired(String jwt){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwt).getPayload().getExpiration().before(new Date());
    }

}
