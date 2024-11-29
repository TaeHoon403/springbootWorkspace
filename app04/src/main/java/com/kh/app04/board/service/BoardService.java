package com.kh.app04.board.service;

import com.kh.app04.board.dao.BoardDao;
import com.kh.app04.board.vo.BoardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardDao dao;

    public int insert(BoardVo vo){
        return dao.insert(vo);
    }

}
