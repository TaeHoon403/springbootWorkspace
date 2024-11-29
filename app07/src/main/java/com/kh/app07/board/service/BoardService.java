package com.kh.app07.board.service;

import com.kh.app07.board.dao.BoardDao;
import com.kh.app07.board.vo.BoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardDao dao;


    public List<BoardVo> selectAll() {
        return dao.selectAll();
    }

    @Transactional
    public int write(BoardVo vo) {

        dao.write(vo);
        
        // 트랜젝션 테스트를 위한 강제로 예외 발생
        int x = 1/0;
        
        dao.write(vo);

        return 1;
    }

}
