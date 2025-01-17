package com.kh.app0116.home.ws;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class KhChatHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> sessionList = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    // websocket 연결 완료
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("웹 소켓 연결~~~~");
        System.out.println("접속지 : "+session.getLocalAddress());

        // 세션 목록에 현재 접속한 세션 정보 추가
        sessionList.add(session);

        // 세션을 통해 메세지 보내기
        Map<String, String> msgMap = new HashMap<>();
        msgMap.put("nick","채팅방에 접속했습니다.");
        msgMap.put("content","");
        msgMap.put("time","");
        String jsonStr = objectMapper.writeValueAsString(msgMap);
        session.sendMessage(new TextMessage(jsonStr));

    }//afterConnectionEstablished

    // websocket 연결 끊김
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("웹 소켓 연결 끊김 ....");

        // 세션 목록에서 연결 끊긴 세션 정보 제거
        sessionList.remove(session);

    }//afterConnectionClosed

    // 메세지 받으면 동작
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("메시지 도착 : "+message.getPayload());

        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        String nowTime = now.format(DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss"));

        // Websocket 통해 전달 된 데이터 꺼내기
        Map<String,String> map = objectMapper.readValue(message.getPayload(), Map.class);
//        String nick = map.get("nick");
        String content = map.get("content");

        // HttpSession 에서 값 꺼내기
        Map<String, Object> sessionAttrMap =  session.getAttributes();
        System.out.println("sessionAttrMap = " + sessionAttrMap);
        String nick = (String) sessionAttrMap.get("nick");

        // json 형식 문자열 만들기
        Map<String, String> msgMap = new HashMap<>();
        msgMap.put("nick",nick);
        msgMap.put("content",content);
        msgMap.put("time",nowTime);
        String jsonStr = objectMapper.writeValueAsString(msgMap);

        // 현재 접속중인 세션에 메시지 뿌리기
        broadCast(jsonStr);

    }//handleTextMessage
    
    // 메시지 브로드케스트
    private void broadCast(String message) throws Exception {
        for (WebSocketSession wss : sessionList) {
            wss.sendMessage(new TextMessage(message));
        }
    }//broadCast
    
}//class
