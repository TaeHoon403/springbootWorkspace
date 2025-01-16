package com.kh.app11.opendata;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RestController
@RequestMapping("api/weather")
public class WeatherApi {

    @GetMapping
    public String getWeather() throws Exception {
        String serviceKey = "";
        String courseId = "179";
        // 서비스 요청할 url 생성
        String str = "https://apis.data.go.kr/1360000/TourStnInfoService1/getTourStnVilageFcst1";
        str += "?ServiceKey="+serviceKey;
        str += "&pageNo=1";
        str += "&numOfRows=10";
        str += "&dataType=JSON";
        str += "&CURRENT_DATE=2025011009";
        str += "&HOUR=24";
        str += "&COURSE_ID="+URLEncoder.encode(courseId, "UTF-8");

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/TourStnInfoService1/getTourStnVilageFcst1"); /* URL */
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + serviceKey); /* Service Key */
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /* 한 페이지 결과 수 */
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /* 요청자료형식(XML/JSON) Default: XML */
        urlBuilder.append("&" + URLEncoder.encode("CURRENT_DATE","UTF-8") + "=" + URLEncoder.encode("2025011009", "UTF-8")); /* 조회 시작일 */
        urlBuilder.append("&" + URLEncoder.encode("HOUR","UTF-8") + "=" + URLEncoder.encode("24", "UTF-8")); /* 조회 할 시간 */
        urlBuilder.append("&" + URLEncoder.encode("COURSE_ID","UTF-8") + "=" + URLEncoder.encode(courseId, "UTF-8")); /* 조회 지역 코드 */
//        URL url = new URL(urlBuilder.toString());
        URL url = new URL(str);

        // connection 생성
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // 통신 방식 설정
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        // 응답 확인
        System.out.println("Response code: " + conn.getResponseCode());

        // 응답 상태에 따라 커넥션에서 데이터 읽어오기
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        /*
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        */

        // 읽어온 데이터 문자열 저장
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        
        // 자원 반납 및 연경 종료
        rd.close();
        conn.disconnect();
        
        // 결과 확인
        return sb.toString();
    }

}
