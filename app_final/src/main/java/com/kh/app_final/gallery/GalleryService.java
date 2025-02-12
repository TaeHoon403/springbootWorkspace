package com.kh.app_final.gallery;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class GalleryService {

    private final GalleryMapper mapper;
    
    // 갤러리 목록 조회
    public List<GalleryVo> findAll(int pno) {
        int limit = 10;
        int offSet = (pno-1)*limit;
        return mapper.findAll(offSet,limit);
    }//findAll


    // 갤러리 파일 추가
    public void write(GalleryVo vo) {
        mapper.write(vo);
    }//write

}//class
