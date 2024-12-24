package com.kh.prac.book.controller;

import com.kh.prac.book.service.BookService;
import com.kh.prac.book.vo.BookVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("book")
@ResponseBody
public class BookController {

    private final BookService service;

    // 도서 등록
    @PostMapping("insert")
    public int insert(BookVo vo){
        return service.insert(vo);
    }//insert

    // 도서 목록 조회
    @GetMapping("list")
    public List<BookVo> list(){
        return service.list();
    }//list

    // 도서 상세 조회
    @GetMapping("detail")
    public BookVo detail(String no){
        return service.detail(no);
    }//detail

    // 도서 삭제
    @GetMapping("delete")
    public int delete(String no){
        return service.delete(no);
    }//delete

}//class
