package com.kh.app_final.common;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// controller 에서 에러 발생 시 동작하는 클래스
//@ControllerAdvice
@RestControllerAdvice // @ControllerAdvice + @ResponseBody
@Slf4j
public class ErrorHandler {

    // 모든 예외에 대한 동작
    @ExceptionHandler(Exception.class)
//    @ResponseBody
    public ResponseEntity<ErrorRespVo> handleException(Exception e, HttpServletResponse resp){

        System.out.println(e);
        log.info(e.getMessage());

        ErrorRespVo errorRespVo = new ErrorRespVo("에러 발생.... 관리자에게 무늬하세요...");
        resp.setStatus(500);

        return ResponseEntity.status(500).body(errorRespVo);

    }//handleException

    // 에러 응답용 객체(vo) 클래스
    @RequiredArgsConstructor
    @Getter
    class ErrorRespVo {
        private final String msg;
    }//innerclass

}//class
