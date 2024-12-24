package com.kh.prac.book.service;

import com.kh.prac.book.mapper.BookMapper;
import com.kh.prac.book.vo.BookVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookMapper mapper;

    // 도서 등록
    public int insert(BookVo vo){
        return mapper.insert(vo);
    }//insert

    // 도서 목록 조회
    public List<BookVo> list(){
        return mapper.list();
    }//list

    // 도서 상세 조회
    public BookVo detail(String no){
        return mapper.detail(no);
    }//detail

    // 도서 삭제
    public int delete(String no){
        return mapper.delete(no);
    }//delete

}//class
