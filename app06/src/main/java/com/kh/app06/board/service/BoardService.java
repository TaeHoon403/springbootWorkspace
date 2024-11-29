package com.kh.app06.board.service;

import com.kh.app06.board.dao.BoardDao;
import com.kh.app06.board.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardDao bd;

    public int write(BoardVo vo){
        return bd.write(vo);
    }

    public List<BoardVo> selectAll(){
        return bd.selectAll();
    }

}
