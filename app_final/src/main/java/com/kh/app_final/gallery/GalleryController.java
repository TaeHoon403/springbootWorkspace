package com.kh.app_final.gallery;

import com.amazonaws.services.s3.AmazonS3;
import com.kh.app_final.jwt.JwtUtil;
import com.kh.app_final.member.MemberVo;
import com.kh.app_final.security.KhUserDetails;
import com.kh.app_final.util.FileUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/gallery")
@Slf4j
public class GalleryController {

    private final GalleryService service;

    private final JwtUtil jwtUtil;

    private final AmazonS3 s3;
    // bucket 명 가져오기
    @Value("${aws.s3.bucket}")
    private String bucket;
    
    // 갤러리 목록 조회
    @GetMapping("list")
    public ResponseEntity<List<GalleryVo>> findAll(@RequestParam(defaultValue = "1")int pno) {

        try {
            List<GalleryVo> result = service.findAll(pno);
            return ResponseEntity.status(200).body(result);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new IllegalStateException("[GALLERY-LIST] 갤러리 목록 조회 실패");
        }

    }//findAll

    // 갤러리 상세 조회
    @GetMapping("detail/{no}")
    public GalleryVo getGalleryVoByNo(@PathVariable("no") String no){

        try {
            return service.getGalleryVoByNo(no);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new IllegalStateException("[GALLERY-DETAIL] 갤러리 상세 조회 실패");
        }

    }//getGalleryVoByNo

    // 갤러리 파일 추가
    @PostMapping("write")
    public void  write(HttpServletRequest req, MultipartFile f, GalleryVo vo,HttpSession session) throws IOException {

        /* // session 에서 작성자 정보 수집
        MemberVo loginMemberVo = (MemberVo) session.getAttribute("loginMemberVo");
        vo.setWriterNo(loginMemberVo.getNo()); */
        // KhUserDetails 에서 작성자 정보 수집
        KhUserDetails khUserDetails = (KhUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long writerNo = khUserDetails.getNo();
        vo.setWriterNo(writerNo);

        try {
            // aws 서버에 파일 업로드 및 파일 url 설정
            vo.setFileUrl(FileUtil.uploadFileToAwsS3(f,s3,bucket));
            vo.setOriginName(f.getOriginalFilename()); // 원본 파일명 설정

            // service 호출
            service.write(vo);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new IllegalStateException("[GALLERY-WRITE] 갤러리 파일 업로드 실패");
        }

    }//insert

}//class
