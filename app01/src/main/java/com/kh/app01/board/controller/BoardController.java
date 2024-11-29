package com.kh.app01.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("board/write")
    public String write(){
        return "board/write";
    }

    @GetMapping("board/list")
    public String boardList(){
        return "board/list";
    }


}
