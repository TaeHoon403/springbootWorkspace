package com.kh.prac01.board.dao;

import com.kh.prac01.board.mapper.BoardMapper;
import com.kh.prac01.board.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardDao {

    @Autowired
    private BoardMapper mapper;

    public int insert(BoardVo vo){
        return mapper.insert(vo);
    }

    public List<BoardVo> BoardList(){
        return mapper.BoardList();
    }

}
