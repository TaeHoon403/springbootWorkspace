package com.kh.SEMI.board.controller;

import com.kh.SEMI.board.service.BoardService;
import com.kh.SEMI.board.vo.AttachmentVo;
import com.kh.SEMI.board.vo.BoardVo;
import com.kh.SEMI.board.vo.CategoryVo;
import com.kh.SEMI.member.vo.MemberVo;
import com.kh.SEMI.util.FileUploader;
import com.kh.SEMI.util.page.PageVo;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    @Value("#{pathInfo.getBoardAttachmentPath()}")
    private String path;

    private final BoardService service;

    // 카테고리 목록 JSON 타입으로 반환
    @GetMapping("category")
    @ResponseBody
    public List<CategoryVo> getCategory(){
        return service.categoryList();
    }//getCategory

    // 게시글 작성 화면 이동
    @GetMapping("write")
    public String write(HttpSession session,Model model) {

        // 로그인 정보 없으면 로그인 화면으로 이동
        if(session.getAttribute("loginInfo") == null){
            return "redirect:/member/login";
        }

        // 카테고리 목록 받아오기
        List<CategoryVo> categoryList = service.categoryList();
        model.addAttribute("categoryList",categoryList);

        return "board/write";

    }//write

    // 게시글 작성 처리
    @PostMapping("write")
    public String write (BoardVo vo, HttpSession session, @RequestParam(name = "f") List<MultipartFile> fileList) throws IOException {

        // 작성자 정보 수집
        MemberVo loginInfo = (MemberVo) session.getAttribute("loginInfo");
        vo.setWriterNo(loginInfo.getNo());

        // 첨부파일 저장
        // iter 쓰면 향상된 for문 자동 완성
        List<String> changeNameList = new ArrayList<>();
        for(MultipartFile f : fileList){
            // 첨부 된 파일 없을 경우
            if(f.isEmpty()){
                break;
            }
            String changeName = FileUploader.save(f,path);
            changeNameList.add(changeName);
        }

        // service 호출
        int result = service.write(vo,changeNameList);

        //결과 처리
        if(result < 1) {
            return "redirect:/error";
        }
        return "redirect:/board/list";

    }//write

    // 게시글 목록 조회(jstl 사용 방식)
    /*@GetMapping("list")

    public String list(Model model){

        // service 호출
        List<BoardVo> boardList = service.getBoardList();

        // 조회된 리스트 반환
        model.addAttribute("boardList",boardList);

        // 결과 반환
        return "board/list";

    }//list*/

    // 게시글 목록 데이터 JSON 파일로 반환
    @GetMapping("list/data")
    @ResponseBody
    public HashMap getBoardList(@RequestParam(name = "pno" , defaultValue = "1") int currentPage, String searchType , String searchValue){
    
        // 페이징 처리 변수 생성
        int listcount = service.getBoardCount(searchType,searchValue);
        int pageLimit = 5;
        int boardLimit = 10;
        
        // 페이징 처리 위한 페이징 데이터 생성
        PageVo pvo = new PageVo(listcount, currentPage, pageLimit, boardLimit);
    
        // 페이지 계산해서 가져온 리스트 목록
        List<BoardVo> boardVoList =  service.getBoardList(pvo,searchType,searchValue);

        // 맵으로 게시글 리스트와 페이징 정보 모두 담는다
        HashMap map = new HashMap();
        map.put("a",boardVoList);
        map.put("b", pvo);

        // service 호출
        return map;
        
    }//getBoardList

    // 게시글 목록 조회 화면으로 이동
    @GetMapping("list")
    public String list(Model model){
        List<CategoryVo> cateVoList = service.categoryList();
        model.addAttribute("cateVoList" , cateVoList);
        return "board/list";
    }//list

    //게시글 상세 조회
    @GetMapping("detail")
    public String detail(String bno , Model model){

        BoardVo vo = service.getBoard(bno);

        List<AttachmentVo> attachmentVoList = service.getAttachmentVoList(bno);
        model.addAttribute("vo" , vo);
        model.addAttribute("attachmentVoList" , attachmentVoList);

        return "board/detail";

    }

    //게시글 삭제 처리
    @GetMapping("del")
    public String del(String bno , HttpSession session){

        int result = service.del(bno);

        if(result != 1){
            throw new IllegalStateException("게시글 삭제 실패 ...");
        }

        session.setAttribute("alertMsg" , "게시글 삭제 성공 !");
        return "redirect:/board/list";

    }

    //게시글 수정 화면 이동
    @GetMapping("edit")
    public void edit(Model model , String bno){
        BoardVo vo = service.getBoard(bno);
        List<CategoryVo> cateVoList = service.categoryList();
        List<AttachmentVo> attachmentVoList = service.getAttachmentVoList(bno);
        model.addAttribute("vo" , vo);
        model.addAttribute("cateVoList" , cateVoList);
        model.addAttribute("attachmentVoList" , attachmentVoList);
    }

    // 게시글 수정 처리
    @PostMapping("edit")
    public String edit (BoardVo vo, HttpSession session, @RequestParam(name ="f")List<MultipartFile> fileList) throws IOException {

        List<String> changeNameList = new ArrayList<>();
        for(MultipartFile f : fileList){
            // 첨부 된 파일 없을 경우
            if(f.isEmpty()){
                break;
            }
            String changeName = FileUploader. save(f,path);
            changeNameList.add(changeName);
        }

        service.update(vo,changeNameList);

        session.setAttribute("alertMsg","게시글 수정 성공!!");

        return "redirect:/board/list";

    }

    // 게시글 첨부파일 삭제
    @PostMapping("attachment/del")
    @ResponseBody
    public int delAttachment(String ano, String fileName){
        System.out.println("===== 삭제되는 파일 정보 =====");
        System.out.println("ano = " + ano);
        System.out.println("fileName = " + fileName);

        int result = service.delAttachment(ano);

        File x = new File("D:\\dev\\springbootWorkspace\\semi\\src\\main\\resources\\static\\" + fileName);
        x.delete();

        return result;
    }

}//class
