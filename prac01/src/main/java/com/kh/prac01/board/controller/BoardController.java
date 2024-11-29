package com.kh.prac01.board.controller;

import com.kh.prac01.board.service.BoardService;
import com.kh.prac01.board.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService service;

    @GetMapping("board/insert")
    public String insert(){
        return "/board/insert";
    }

    @PostMapping("board/insert")
    public String insert(BoardVo vo){
        int result = service.insert(vo);
        return "redirect:/board/list";
    }

    @GetMapping("board/list")
    public String boardList(H){
        List<BoardVo> boardList = service.BoardList();

        return "board/list";
    }
}
