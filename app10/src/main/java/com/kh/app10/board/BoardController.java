package com.kh.app10.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/board")
@CrossOrigin(value="http://localhost:3000/")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService service;

    // 게시글 리스트 조회
    @GetMapping
    public List<BoardVo> getBoardList(){

        return service.getBoardList();

    }//getBoardList

}//class
