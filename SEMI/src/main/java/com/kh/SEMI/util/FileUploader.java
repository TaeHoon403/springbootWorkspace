package com.kh.SEMI.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class FileUploader {

    // 서버에 파일 저장
    public static String save(MultipartFile f,String path) throws IOException {

        // 전달받은 파일 있으면 서버에 저장
        if(!f.isEmpty()){
            // 업로드 파일의 원본 파일명 수집
            String originFileName = f.getOriginalFilename();
            // 확장자명 수집
            String ext = originFileName.substring(originFileName.lastIndexOf("."));
            // 임의 문자열 값으로 새 파일명 생성
            String changeName = UUID.randomUUID().toString() + ext;
            // 파일 객체 생성
            File targetFile = new File(path+changeName);
            //파일 이름 변경
            f.transferTo(targetFile);

            // 결과 반환
            return changeName;
        }

        // 결과 반환
        return null;

    }
    
}
