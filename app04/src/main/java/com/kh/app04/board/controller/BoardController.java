package com.kh.app04.board.controller;

import com.kh.app04.board.service.BoardService;
import com.kh.app04.board.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService service;

    @GetMapping("board/insert")
    public String insert(){
        return "board/insert";
    }

    @PostMapping("board/insert")
    public void insert(BoardVo vo){
        // data -> obj
            // vo 형태로 가져움
        System.out.println("title ::::::"+vo.getTitle());

        // service
        int result = service.insert(vo);

        // result
        if(result == 1){
            System.out.println("게시글 작성 성공!!");
        }
        else{
            System.out.println("게시글 작성 실패...");
        }

    }

}
