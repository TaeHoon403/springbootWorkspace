package com.kh.app0131.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    
    // 생성자에서 secretKey 생성
    private final SecretKey secretKey;
    public JwtUtil(@Value("${kh.str}") String secret){
        byte[] bytes = secret.getBytes(StandardCharsets.UTF_8);
        this.secretKey = new SecretKeySpec(bytes,"HmacSHA256");
    }//secretKey

    // 토큰 생성
    public String createToken(Long no, String nick, long expTime ) {

        return Jwts.builder()
                .claim("no",no) // 토큰에 정보 추가
                .claim("nick",nick) // 토큰에 정보 추가
                .issuedAt(new Date(System.currentTimeMillis())) // 발급 시간 기록
                .expiration(new Date(System.currentTimeMillis()+expTime)) // 만료 시간 설정
                .signWith(secretKey) // 서명 추가
                .compact() // 토큰 생성 완료
                ;

    }//createToken
    
    // 토큰 검증 - 나머지 검증 메서드에 토큰 검증이 내포되어 있어 굳이 따로 해주지 않아도 된다
//    public boolean checkToken(String token){}//checkToken

    // 토큰 만료여부 검증
    public boolean isExpire(String token) {
        // before(new Date()) -> 해당 expiration 값이 입력한 시간을 지나지 않았으면 true
        // 우리 코드로는 expiration 값이 더 미래여야 만료가 아니기 때문에 false 가 나오면 만료되지 안았다로 통과시켜야함
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }//isExpire

    // 토큰에서 no 값 꺼내기
    public Long getNo(String token) {
        return  Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("no",Long.class);
    }//getNo

    // 토큰에서 nick 값 꺼내기
    public String getNick(String token) {
        return  Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("nick",String.class);
    }//getNick


}//class
