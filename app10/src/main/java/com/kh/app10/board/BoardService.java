package com.kh.app10.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BoardService {

    private final BoardMapper mapper;

    // 게시글 리스트 조회
    public List<BoardVo> getBoardList() {
        return mapper.getBoardList();
    }//getBoardList

}//class
