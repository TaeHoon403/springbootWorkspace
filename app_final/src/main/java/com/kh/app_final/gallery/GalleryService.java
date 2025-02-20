package com.kh.app_final.gallery;

import com.kh.app_final.common.util.PageVo;
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
    public List<GalleryVo> findAll(PageVo pageVo) {
        int limit = pageVo.getBoardLimit();
        int offSet = pageVo.getOffset();
        return mapper.findAll(offSet,limit);
    }//findAll

    // 갤러리 파일 추가
    public void write(GalleryVo vo) {
        mapper.write(vo);
    }//write

    // 갤러리 상세 조회
    public GalleryVo getGalleryVoByNo(String no) {
        return mapper.getGalleryVoByNo(no);
    }//getGalleryVoByNo

    // 갤러리 페이지 수 조회
    public int getListCount() {
        return mapper.getListCount();
    }//getPageCount

}//class
