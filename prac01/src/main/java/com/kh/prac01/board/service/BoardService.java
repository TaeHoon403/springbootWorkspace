package com.kh.prac01.board.service;

import com.kh.prac01.board.dao.BoardDao;
import com.kh.prac01.board.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardDao dao;

    public int insert(BoardVo vo){
        return dao.insert(vo);
    }

    public List<BoardVo> BoardList(){
        return dao.BoardList();
    }

}
