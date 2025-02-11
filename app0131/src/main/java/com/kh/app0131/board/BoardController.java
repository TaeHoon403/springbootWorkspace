package com.kh.app0131.board;

import com.kh.app0131.member.MemberVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("api/board")
public class BoardController {

    // 게시글 작성
    @PostMapping("write")
    public String write(@RequestBody BoardVo vo, HttpSession session){
        System.out.println("제 목 ::: "+vo.getTitle());
        System.out.println("내 용 ::: "+vo.getContent());
        MemberVo loginVo = (MemberVo) session.getAttribute("loginMemberVo");
        System.out.println("작성자 ::: "+loginVo.getNick());
        return "board write success~~~";
    }//write

}//class
