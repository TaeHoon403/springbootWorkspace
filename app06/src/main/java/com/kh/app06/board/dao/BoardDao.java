package com.kh.app06.board.dao;

import com.kh.app06.board.mapper.BoardMapper;
import com.kh.app06.board.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardDao {

    private final BoardMapper bm;

    public int write(BoardVo vo){
        return bm.write(vo);
    }

    public List<BoardVo> selectAll(){
        return bm.selectAll();
    }

}
