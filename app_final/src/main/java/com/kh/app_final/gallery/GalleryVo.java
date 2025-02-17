package com.kh.app_final.gallery;

import lombok.Data;

@Data
public class GalleryVo {

    private Long no;
    private Long writerNo;
    private String title;
    private String content;
    private String enrollDate;
    private String originName;
    private String fileUrl;
    private String delYn;

}
