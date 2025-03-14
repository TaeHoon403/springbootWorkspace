package com.kh.app_final.gallery;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GalleryMapper {

    // 갤러리 목록 조회
//    @Select("""
//            SELECT
//            NO
//            , WRITER_NO
//            , TITLE
//            , CONTENT
//            , ENROLL_DATE
//            , ORIGIN_NAME
//            , FILE_URL
//            , DEL_YN
//            FROM GALLERY
//            WHERE DEL_YN ='N'
//            ORDER BY NO DESC
//            OFFSET #{offSet} ROWS FETCH NEXT #{limit} ROWS ONLY
//            """)
    @Select("""
            SELECT *
                        FROM (
                            SELECT
                                NO,
                                WRITER_NO,
                                TITLE,
                                CONTENT,
                                ENROLL_DATE,
                                ORIGIN_NAME,
                                FILE_URL,
                                DEL_YN,
                                ROWNUM AS RNUM
                            FROM (
                                SELECT
                                    NO,
                                    WRITER_NO,
                                    TITLE,
                                    CONTENT,
                                    ENROLL_DATE,
                                    ORIGIN_NAME,
                                    FILE_URL,
                                    DEL_YN
                                FROM GALLERY
                                WHERE DEL_YN = 'N'
                                ORDER BY NO DESC
                            )
                            WHERE ROWNUM <= #{offset} + #{limit}
                        )
                        WHERE RNUM > #{offset}
            """)
    List<GalleryVo> findAll(int offset,int limit);
    
    // 갤러리 파일 추가
    @Insert("""
            INSERT INTO GALLERY
            (
            NO
            , WRITER_NO
            , TITLE
            , CONTENT
            , ORIGIN_NAME
            , FILE_URL
            )
            VALUES
            (
            SEQ_GALLERY.NEXTVAL
            , #{writerNo}
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

    // 갤러리 페이지 수 조회
    @Select("""
            SELECT 
            COUNT(NO)
            FROM GALLERY
            WHERE DEL_YN = 'N'
            """)
    int getListCount();

    // 갤러리 삭제
    @Update("""
            UPDATE GALLERY
            SET
                DEL_YN = 'Y'
            WHERE NO = #{no}
            """)
    void delete(Long no);
    
}//class
