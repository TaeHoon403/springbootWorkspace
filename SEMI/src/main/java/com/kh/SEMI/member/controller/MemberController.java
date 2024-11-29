package com.kh.SEMI.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.SEMI.member.service.MemberService;
import com.kh.SEMI.member.vo.MemberVo;
import com.kh.SEMI.util.FileUploader;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

@RequestMapping("member")
@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    // 공통으로 사용할 경로 정보를 application.properties 에 저장해두고 불러서 사용할 수 있다
    // @Value("${x}") (@를 사용하면 속성 값을 있는 그대로 가져옴)
    @Value("#{pathInfo.getProfilePath()}") // #을 사용하면 표현식을 사용한 수 있다
    private String profilePath;

    private final MemberService service;

    // 회원가입 화면 이동
    @GetMapping("join")
    public void join(){}//join

    // 회원가입 처리
    @PostMapping("join")
    public String join(MemberVo vo, MultipartFile f, HttpSession session) throws Exception {

        // 파일을 서버에 저장
        String changeName = FileUploader.save(f,profilePath);
        vo.setProfile(changeName);

        // service 호출
        int result = service.join(vo);
        
        // 결과 처리
        if(result != 1){
            String errorMsg = "[MEMBER-J-001] 회원가입 진행 중 오류가 발생했습니다.";
            log.warn(errorMsg);
            throw new Exception(errorMsg);
        }

        session.setAttribute("alertMsg","회원가입 성공!!");
        return "redirect:/member/login";

    }//join

    // 로그인 화면 이동
    @GetMapping("login")
    public void login(){}//login

    // 로그인 처리
    @PostMapping("login")
    public String login(MemberVo vo, HttpSession session) throws Exception {

        // service 호출
        MemberVo loginInfo = service.login(vo);

        // 결과 처리
        if(loginInfo == null) {
            String errorMsg = "[MEMBER-L-001] 로그인 진행 중 오류가 발생했습니다.";
            log.warn(errorMsg);
            throw new Exception(errorMsg);
        }
        session.setAttribute("loginInfo",loginInfo);
        return "redirect:/home";

    }// login

    // 로그아웃
    @GetMapping("logout")
    public String logout(HttpSession session){
//        session.setAttribute("loginInfo",null);
        session.removeAttribute("loginInfo");
        session.setAttribute("alertMsg","로그아웃 되었습니다.");
        return "redirect:/home";

    }//logout

    // 마이페이지 이동
    @GetMapping("mypage")
    public String mypage(HttpSession session){

        // 비즈니스 로직(로그인 정보 없으면 마이페이지 접근 불가)
        if(session.getAttribute("loginInfo") == null){
            return "redirect:/member/login";
        }
        return "member/mypage";

    }//mypage

    // 개인정보 수정 처리
    @PostMapping("edit")
    public String edit(MemberVo vo, HttpSession session) throws Exception {

        if(vo.getPwd().length() == 0){
            vo.setPwd(null);
        }

        // 계정 번호 수집
        MemberVo loginInfo =  (MemberVo) session.getAttribute("loginInfo");
        vo.setNo(loginInfo.getNo());

        // service 호출
        MemberVo updateInfo = service.edit(vo);

        // 결과 처리
        if(updateInfo == null) {
            String errorMsg = "[MEMBER-E-001] 회원정보 수정 중 오류가 발생했습니다.";
            log.warn(errorMsg);
            throw new Exception(errorMsg);
        }
        session.setAttribute("loginInfo", updateInfo);
        return "redirect:/member/mypage";

    }//edit

    // 회원탈퇴 화면 이동
    @GetMapping("quit")
    public void quit(){}//quit

    // 회원탈퇴 처리
    @PostMapping("quit")
    public String quit(MemberVo vo, HttpSession session) throws Exception {

        // 계정 번호 수집
        MemberVo loginInfo =  (MemberVo) session.getAttribute("loginInfo");
        vo.setNo(loginInfo.getNo());

        // service 호출
        int result = service.quit(vo);

        // 결과 처리
        if(result != 1) {
            String errorMsg = "[MEMBER-Q-001] 회원탈퇴 중 오류가 발생했습니다.";
            log.warn(errorMsg);
            throw new Exception(errorMsg);
        }
        session.removeAttribute("loginInfo");
        return "redirect:/home";

    }//quit

    // 아이디 중복체크 처리
    @PostMapping("dup-id")
    @ResponseBody
    public String checkDupId(String id) throws Exception {

        // 객체 생성
        HashMap<String, String> map =  new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        map.put("data",id);

        // service 호출
        boolean isDup = service.checkDupId(id);

        // 결과 처리
        if(isDup){
            map.put("status","fail");
        }
        else{
            map.put("status","pass");
        }
        
        // 객체를 Json 타입으로 변환
        String str = objectMapper.writeValueAsString(map);
        
        // 결과 반환
        return str;

    }//checkDupId

}//class
