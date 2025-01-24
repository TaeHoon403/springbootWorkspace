package com.kh.app0120.ws;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessageSendingOperations messageSendingOperations;
    
    // 웹 소켓 연결 시 동작
    @EventListener
    public void handleConnect(SessionConnectEvent event){
        System.out.println("[웹 소켓 연결 성공]  event :"+event);
    }//handleConnect


    // 요청 처리하는 공통 메서드
    public void handelMsg(Map<String,String> map){
        String channelId = map.get("channelId");
        String topic = map.get("topic");
        System.out.println("["+topic+"] str = " + map.get("payload"));

        messageSendingOperations.convertAndSend("/sub/"+topic+"/"+channelId,map);
    }//MessageMapping

    // java 라는 요청 발생 시 동작
    @MessageMapping("/java")
    public void java(Map<String,String> map){handelMsg(map);}//MessageMapping

    // oracle 라는 요청 발생 시 동작
    @MessageMapping("/oracle")
    public void oracle(Map<String,String> map){handelMsg(map);}//MessageMapping

    // kh 라는 요청 발생 시 동작
    @MessageMapping("/kh")
    public void kh(Map<String,String> map){handelMsg(map);}//MessageMapping

    // 웹 소켓 연결 끊길 경우 동작
    @EventListener
    public void handleDisconnect(SessionConnectEvent event){
        System.out.println("[웹 소켓 연결 종료]  event :"+event);
    }

}//class
