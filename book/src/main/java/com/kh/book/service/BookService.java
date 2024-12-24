package com.kh.book.service;

import com.kh.book.mapper.BookMapper;
import com.kh.book.vo.BookVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BookService {

    private final BookMapper mapper;

    // 도서 등록
    public int insert(BookVo vo) {
        return mapper.insert(vo);
    }
    
    // 도서 목록 조회
    public List<BookVo> list() {
        return mapper.list();
    }

    // 도서 상세 조회
    public BookVo detail(String no) {
        return mapper.detail(no);
    }

    // 도서 삭제
    public int delete(String no) {
        return mapper.delete(no);
    }

}
