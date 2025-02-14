package com.kh.app_final.gallery;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GalleryMapper {

    // 갤러리 목록 조회
    @Select("""
            SELECT
            NO
            , WRITER_NO
            , TITLE
            , CONTENT
            , ENROLL_DATE
            , ORIGIN_NAME
            , FILE_URL
            , DEL_YN
            FROM GALLERY
            WHERE DEL_YN ='N'
            ORDER BY NO DESC
            OFFSET #{offSet} ROWS FETCH NEXT #{limit} ROWS ONLY
            """)
    List<GalleryVo> findAll(int offSet,int limit);
    
    // 갤러리 파일 추가
    @Insert("""
            INSERT INTO GALLERY
            (
            WRITER_NO
            , TITLE
            , CONTENT
            , ORIGIN_NAME
            , FILE_URL
            )
            VALUES
            (
            #{writerNo}
            , #{title}
            , #{content}
            , #{originName}
            , #{fileUrl}
            )
            """)
    void write(GalleryVo vo);

    // 갤러리 상세 조회
    @Select("""
            SELECT
            NO
            , WRITER_NO
            , TITLE
            , CONTENT
            , ENROLL_DATE
            , ORIGIN_NAME
            , FILE_URL
            , DEL_YN
            FROM GALLERY
            WHERE NO = #{no}
            AND DEL_YN ='N'
            """)
    GalleryVo getGalleryVoByNo(String no);
    
}//class
