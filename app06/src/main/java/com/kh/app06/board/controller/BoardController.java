package com.kh.app06.board.controller;

import com.kh.app06.board.service.BoardService;
import com.kh.app06.board.vo.BoardVo;
import com.kh.app06.member.vo.MemberVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {

    private final BoardService bs;

    // 게시글 작성 처리
    @PostMapping("write")
    public String write(BoardVo vo, HttpSession session){

        MemberVo loginInfo = (MemberVo) session.getAttribute("loginInfo");
        vo.setWriterNo(loginInfo.getNo());

        int result = bs.write(vo);

        if(result == 1){
            return "redirect:/board/list";
        }
        else {
            return "redirect:/error";
        }
    }

    // 게시글 목록 처리
    @GetMapping("list")
    public String selectAll(){

        List<BoardVo> boardList = bs.selectAll();

        System.out.println("boardList = " + boardList);

        return boardList.toString();
    }

}

