package com.kh.notionapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class TestClass {

    @GetMapping("test")
    public String m01() throws  Exception {

        /////////////////////// HTTP 요청 전송 및 응답 받기 ///////////////////////

        // 변수 설정
        String databaseId = "1544c27260bd80b783e5d32fb26de68f"; // 정보 가져올 데이터베이스 id
        String secretKey = "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";// 노션의 인증키
        String notionVersion ="2022-06-28";// 사용한 노션 버전

        // 요청 보낼 URL 설정
        URL url = new URL("https://api.notion.com/v1/databases/" + databaseId + "/query");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // 예외 발생함 -> throws Exception 달아주기

        // 요청 방식 (method) 설정
        connection.setRequestMethod("POST");

        // 전송할 data (header) 설정
        connection.setRequestProperty("Authorization", secretKey);
        connection.setRequestProperty("Notion-Version", notionVersion);
        connection.setRequestProperty("Content-Type", "application/json");

        // 요청에 대한 응답 상태 (HTTP status code) 받기
        int responseCode = connection.getResponseCode();
        System.out.println("responseCode = " + responseCode);
        
        // 응답 받은 데이터 읽기
        StringBuilder response = new StringBuilder();
        InputStream is = connection.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);
        String str;
        while ((str = reader.readLine()) != null) {
            response.append(str.trim());
        }
        System.out.println("response = " + response);
        return response.toString();

    }

}
