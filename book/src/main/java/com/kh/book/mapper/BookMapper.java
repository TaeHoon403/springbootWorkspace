package com.kh.book.mapper;

import com.kh.book.vo.BookVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BookMapper {

    // 도서 등록
    @Insert("""
            INSERT INTO BOOK
            (
                NO
                , TITLE
                , WRITER
                , PRICE
                , PUBLISHED_DATE
            )
            VALUES
            (
                SEQ_BOOK.NEXTVAL
                , #{title}
                , #{writer}
                , #{price}
                , #{publishedDate}
            )
            """)
    int insert(BookVo vo);

    // 도서 목록 조회
    @Select("""
            SELECT
                NO
                , TITLE
                , WRITER
                , PRICE
                , PUBLISHED_DATE
                , DEL_YN
            FROM BOOK
            WHERE DEL_YN = 'N'
            ORDER BY NO DESC
            """)
    List<BookVo> list();

    // 도서 상세 조회
    @Select("""
            SELECT
                NO
                , TITLE
                , WRITER
                , PRICE
                , PUBLISHED_DATE
                , DEL_YN
            FROM BOOK
            WHERE NO = #{no}
            AND DEL_YN = 'N'
            """)
    BookVo detail(String no);

    // 도서 삭제
    @Update("""
            UPDATE BOOK
            SET
                DEL_YN = 'Y'
            WHERE NO = #{no}
            """)
    int delete(String no);

}
