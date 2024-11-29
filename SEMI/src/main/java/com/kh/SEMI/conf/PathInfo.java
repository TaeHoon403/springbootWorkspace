package com.kh.SEMI.conf;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class PathInfo {

    // OS 에 따른 경로 구분자 값 생성
    String sep = File.separator;

    // 고정된 경로 변수 저장
    private String staticPath = "D:" + sep
            + "dev" + sep
            + "springbootWorkspace" + sep
            + "semi" + sep
            + "src" + sep
            + "main" + sep
            + "resources" + sep
            + "static" + sep;

    public String getProfilePath(){

        String path  = staticPath
                + "img" + sep
                + "profile"+sep;
        return path;

    }

    public String getBoardAttachmentPath(){

        String path  = staticPath
                + "img" + sep
                + "board"+sep
                + "attachment"+sep;
        return path;

    }

}
