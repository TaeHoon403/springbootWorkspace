package com.kh.app05.board.service;

import com.kh.app05.board.dao.BoardDao;
import com.kh.app05.board.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardDao bd;

    public int write(BoardVo vo) {
        return bd.write(vo);
    }

    public List<BoardVo> selectBoardList() {
        return bd.selectBoardList();
    }
}
