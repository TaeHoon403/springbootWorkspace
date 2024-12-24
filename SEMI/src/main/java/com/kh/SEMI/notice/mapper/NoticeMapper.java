package com.kh.SEMI.notice.mapper;

import com.kh.SEMI.admin.vo.NoticeReplyVo;
import com.kh.SEMI.notice.vo.NoticeVo;
import com.kh.SEMI.util.page.PageVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface NoticeMapper {

    // 공지사항 작성
    @Insert("""
            INSERT INTO NOTICE
            (
                NO
                , TITLE
                , CONTENT
                , WRITER_NO
            )
            VALUES
            (
                SEQ_NOTICE.NEXTVAL
                , #{title}
                , #{content}
                , #{writerNo}
            )
            """)
    int write(NoticeVo vo);
    
    // 공지사항 목록 조회
    @Select("""
            SELECT
                N.NO
                , N.TITLE
                , N.CONTENT
                , N.WRITER_NO
                , A.NICK AS WRITER_NICK
                , N.HIT
                , N.CREATE_DATE
                , N.DEL_YN
            FROM NOTICE N
            JOIN ADMIN A ON (N.WRITER_NO = A.NO)
            WHERE N.DEL_YN = 'N'
            ${str}
            ORDER BY N.NO DESC
            OFFSET #{pvo.offset} ROWS FETCH NEXT #{pvo.boardLimit} ROWS ONLY
            """)
    List<NoticeVo> getNoticeList(PageVo pvo, String str);
    
    // 공지사항 갯수 조회
    @Select("""
            SELECT COUNT(NO)
            FROM NOTICE
            WHERE DEL_YN = 'N'
            ${str}
            """)
    int getNoticeCnt(String str);

    // 공지사항 상세 조회
    @Select("""
            SELECT
                N.NO
                , N.TITLE
                , N.CONTENT
                , N.WRITER_NO
                , A.NICK AS WRITER_NICK
                , N.HIT
                , N.CREATE_DATE
                , N.DEL_YN
            FROM NOTICE N
            JOIN ADMIN A ON (N.WRITER_NO = A.NO)
            WHERE N.NO = #{no}
            AND N.DEL_YN = 'N'
            """)
    NoticeVo getNoticeByNo(String no);

    // 공지사항 삭제
    @Update("""
            UPDATE NOTICE
            SET
                DEL_YN = 'Y'
            WHERE NO IN(${x})
            """)
    int delete(String x);

    //공지사항 수정
    @Update("""
            UPDATE NOTICE
            SET
                TITLE = #{title}
                , CONTENT = #{content}
            WHERE NO = #{no}
            AND DEL_YN = 'N'
            AND WRITER_NO = #{writerNo}
            """)
    int edit(NoticeVo vo);

    // 공지사항 댓글 작성
    @Insert("""
            INSERT INTO NOTICE_REPLY
            (
                NO
                , CONTENT
                , REF_NO
                , WRITER_NO
            )
            VALUES
            (
                SEQ_NOTICE_REPLY.NEXTVAL
                , #{content}
                , #{refNo}
                , #{writerNo}
            )
            """)
    int replyWrite(NoticeReplyVo vo);


    // 공지사항 댓글 목록 조회
    @Select("""
            SELECT
                NR.NO
                , NR.CONTENT
                , NR.REF_NO
                , NR.WRITER_NO
                , M.NICK AS WRITER_NICK
                , NR.CREATE_DATE
                , NR.DEL_YN
            FROM NOTICE_REPLY NR
            JOIN MEMBER M ON (NR.WRITER_NO = M.NO)
            WHERE NR.REF_NO = #{noticeNo}
            AND NR.DEL_YN = 'N'
            ORDER BY NR.CREATE_DATE DESC
            """)
    List<NoticeReplyVo> getNoticeReplyList(String noticeNo);

}//interface
