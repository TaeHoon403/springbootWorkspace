package com.kh.book.controller;

import com.kh.book.service.BookService;
import com.kh.book.vo.BookVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("book")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(value = "http://192.168.40.106:5500/")
@ResponseBody
public class BookController {

    private final BookService service;


    // 도서 등록
    @GetMapping("insert")
    public void insert(){}

    @PostMapping("insert")
//    public String insert(BookVo vo){
    public int insert(@RequestBody BookVo vo){

        int result = service.insert(vo);

//        return "redirect:/book/list";
        return result;
    }

    // 도서 목록 조회
    @GetMapping("list")
//    public String list(Model model){
    public List<BookVo> list(Model model){

        List<BookVo> bookList = service.list();

        model.addAttribute("vo",bookList);

//        return "book/list";
        return bookList;

    }

    // 도서 상세 조회
    @GetMapping("detail")
//    public String detail(Model model, String no){
    public BookVo detail(Model model, String no){

        BookVo vo = service.detail(no);

        model.addAttribute("vo",vo);

//        return "book/detail";
        return vo;

    }

    // 도서 삭제
    @GetMapping("delete")
//    public String delete(String no){
    public int delete(String no){

        int result = service.delete(no);

//        return "redirect:/book/list";
        return result;

    }

}
