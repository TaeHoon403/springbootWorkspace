package com.kh.SEMI.notice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.SEMI.admin.vo.AdminVo;
import com.kh.SEMI.notice.service.NoticeService;
import com.kh.SEMI.notice.vo.NoticeVo;
import com.kh.SEMI.util.page.PageVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("notice")
@RequiredArgsConstructor
@Slf4j
public class NoticeController {

    private final ObjectMapper objectMapper;
    private final NoticeService service;

    // 공지사항 작성 화면 이동
    @GetMapping("write")
    public String write(HttpSession session){
        
        // 로그인 된 관리자정보 수집
        AdminVo loginAdminVo = (AdminVo) session.getAttribute("loginAdminVo");

        // 예외처리
        if(loginAdminVo != null){
            return "notice/write";
        }
        
        // 화면 이동
        return"redirect:/admin/login";

    }//write

    // 공지사항 작성 처리
    @PostMapping("write")
    public String write(NoticeVo vo, HttpSession session){

        // 게시글 작성자 정보 수집
        AdminVo adminVo = (AdminVo) session.getAttribute("loginAdminVo");
        vo.setWriterNo(adminVo.getNo());

        // service 호출
        int result = service.write(vo);
        
        // 예외처리
        if(result != 1){
            throw new IllegalStateException("[ERROR] 공지사항 작성 중 오류 발생");
        }

        // 화면 이동
        return "redirect:/notice/list";

    }//write

    // 공지사항 목록 조회 처리 + 검색
    @GetMapping("list")
    public String list(@RequestParam(name = "pno", defaultValue="1") int currentPage, Model model , String searchValue){

        // 페이징 처리
        int listCount = service.getNoticeCnt(searchValue);
        int pageLimit = 5;
        int boardLimit = 10;
        PageVo pvo = new PageVo(listCount, currentPage, pageLimit, boardLimit);

        // service 호출
        List<NoticeVo> noticeVoList = service.getNoticeList(pvo,searchValue );
        model.addAttribute("voList",noticeVoList);
        model.addAttribute("pvo",pvo);
        // 검색 시 검색어 유지를 위해 다시 리턴
        model.addAttribute("searchValue",searchValue);

        // 화면 이동
        return "notice/list";

    }//list

    //공지사항 상세 조회 처리
    @GetMapping("detail")
    public String detail (String no, Model model){

        // service 호출
        NoticeVo vo = service.noticeByNo(no);
        model.addAttribute("vo",vo);

        // 화면 이동
        return "notice/detail";

    }//detail

    // 공지사항 삭제 처리
    @DeleteMapping("delete")
    @ResponseBody
    public String delete(String noticeNoArr) throws JsonProcessingException {

        // 삭제할 공지사항 번호 리스트로 변환
        List<String> noticeNoList = objectMapper.readValue(noticeNoArr, List.class);

        // service 호출
        int result = service.delete(noticeNoList);

        // 예외처리
        if (result != noticeNoList.size()){
            return "fail";
        }

        // 결과 반환
        return "success";

    }//delete


    // 공지사항 수정 화면 이동


    // 공지사항 수정 처리

}//class
