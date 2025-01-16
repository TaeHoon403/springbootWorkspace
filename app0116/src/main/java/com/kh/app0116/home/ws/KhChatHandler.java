package com.kh.app0116.home.ws;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Queue;

@Component
public class KhChatHandler extends TextWebSocketHandler {

//    Queue msgQueue =

    // websocket 연결 완료
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
//        super.afterConnectionEstablished(session);
        System.out.println("웹 소켓 연결~~~~");
        System.out.println("접속지 : "+session.getLocalAddress());

        // 세션을 통해 메세지 보내기
        session.sendMessage(new TextMessage("채팅방에 접속했습니다."));
    }

    // websocket 연결 끊김
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
//        super.afterConnectionClosed(session, status);
        System.out.println("웹 소켓 연결 끊김 ....");
    }

    // 메세지 받으면 동작
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        super.handleTextMessage(session, message);
        System.out.println("메시지 도착 : "+message.getPayload());
        session.sendMessage(new TextMessage(message.getPayload()));
    }
    
}
