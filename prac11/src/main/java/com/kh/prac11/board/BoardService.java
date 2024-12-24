package com.kh.prac11.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardMapper mapper;


    public List<BoardVo> getBoardList() {
        return mapper.getBoardList();
    }//getBoardList

    public int insertBoard(BoardVo vo) {
        return mapper.insertBoard(vo);
    }//insertBoard

    public BoardVo getBoardByNo(Long no) {
        return mapper.getBoardByNo(no);
    }//getBoardByNo

}//service
