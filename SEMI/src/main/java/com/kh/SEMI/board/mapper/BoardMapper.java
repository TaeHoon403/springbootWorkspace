package com.kh.SEMI.board.mapper;

import com.kh.SEMI.board.vo.AttachmentVo;
import com.kh.SEMI.board.vo.BoardVo;
import com.kh.SEMI.board.vo.CategoryVo;
import com.kh.SEMI.util.page.PageVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BoardMapper {

    // 게시글 insert
    @Insert("""
            INSERT INTO BOARD
            (
                NO
                , TITLE
                , CONTENT
                , CATEGORY_NO
                , WRITER_NO
            )
            VALUES
            (
                SEQ_BOARD.NEXTVAL
                , #{title}
                , #{content}
                , #{categoryNo}
                , #{writerNo}
            )
            """)
    int write(BoardVo vo);

    int insertBoardAttachment(List<String> changeNameList);

    // 게시글 select All
//    @Select("""
//            SELECT
//                NO
//                , TITLE
//                , CONTENT
//                , CATEGORY_NO
//                , WRITER_NO
//                , HIT
//                , CREATE_DATE
//                , DEL_YN
//            FROM BOARD
//            WHERE DEL_YN = 'N'
//            ORDER BY NO DESC
//            """)
//    @Select("""
//            SELECT
//                B.NO
//                , B.TITLE
//                , B.CONTENT
//                , B.CATEGORY_NO
//                , C.NAME AS CATEGORY_NAME
//                , B.WRITER_NO
//                , M.NICK As WRITER_NICK
//                , B.HIT
//                , B.CREATE_DATE
//                , B.DEL_YN
//            FROM BOARD B
//            JOIN CATEGORY C ON (B.CATEGORY_NO = C.NO)
//            JOIN MEMBER M ON (B.WRITER_NO = M.NO)
//            WHERE B.DEL_YN = 'N'
//            ORDER BY B.NO DESC
//            OFFSET #{offset} ROWS FETCH NEXT #{boardLimit} ROWS ONLY
//            """)
    List<BoardVo> getBoardList(PageVo pvo, String searchType, String searchValue);

    // 카테고리 SELECT ALL
    @Select("""
            SELECT *
            FROM CATEGORY
            """)
    List<CategoryVo> categoryList();

    // 전체 게시글 수 조회
//    @Select("""
//            SELECT COUNT(NO)
//            FROM BOARD
//            WHERE DEL_YN = 'N'
//            """)
    int getBoardCount(String searchType, String searchValue);

    // 게시글 select By NO
    @Select("""
            SELECT
                B.NO
                ,B.TITLE
                ,B.CONTENT
                ,B.CATEGORY_NO
                ,B.WRITER_NO
                ,B.HIT
                ,B.CREATE_DATE
                ,B.DEL_YN
                ,M.NICK AS WRITER_NICK
                ,C.NAME AS CATEGORY_NAME
            FROM BOARD B
            JOIN MEMBER M ON (B.WRITER_NO = M.NO)
            JOIN CATEGORY C ON (B.CATEGORY_NO = C.NO)
            WHERE B.NO = #{bno}
            AND B.DEL_YN = 'N'
            """)
    BoardVo getBoard(String bno);

    // 게시글 HIT Update By NO
    @Update("""
            UPDATE BOARD
                SET
                    HIT = HIT + 1
            WHERE NO = #{bno}
            AND DEL_YN = 'N'
            """)
    int increaseHit(String bno);

    // 게시글 삭제 BY NO
    @Update("""
            UPDATE BOARD
                SET
                    DEL_YN = 'Y'
            WHERE NO = #{no}
            """)
    int del(String bno);

    // 게시글 수정 BY NO
    @Update("""
            UPDATE BOARD
                SET 
                    TITLE = #{title}
                    , CONTENT = #{content}
                    , CATEGORY_NO = #{categoryNo}
                WHERE NO = #{no}
                AND DEL_YN = 'N'
            """)
    int update(BoardVo vo);

    // 첨부파일 목록 조회 BY 게시글NO
    @Select("""
            SELECT *
            FROM BOARD_ATTACHMENT
            WHERE REF_NO = #{bno}
            AND DEL_YN = 'N'
            ORDER BY NO DESC
            """)
    List<AttachmentVo> getAttachmentVoList(String bno);

    // 첨부파일 삭제 BY 게시글NO
    @Update("""
            UPDATE BOARD_ATTACHMENT
                SET 
                    DEL_YN = 'Y'
                WHERE NO = #{ano}
            """)
    int delAttachment(String ano);

    // 첨부파일 수정 BY 게시글NO
    int updateBoardAttachment(List<String> changeNameList, String no);

    // 게시글 검색

}
