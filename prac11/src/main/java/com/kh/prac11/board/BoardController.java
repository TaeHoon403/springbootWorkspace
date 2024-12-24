package com.kh.prac11.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/board")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService service;

    @GetMapping
    public List<BoardVo> getBoardList(){
        return service.getBoardList();
    }//getBoardList

    @PostMapping
    public int insertBoard(@RequestBody BoardVo vo){
        return service.insertBoard(vo);
    }//insertBoard

    @GetMapping("{no}")
    public BoardVo getBoardByNo(@PathVariable Long no){
        return service.getBoardByNo(no);
    }//getBoardByNo

}//controller
