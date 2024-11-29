package com.kh.app05.board.mapper;

import com.kh.app05.board.vo.BoardVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Insert("""
            INSERT INTO BOARD (NO,TITLE,CONTENT,WRITER_NO)
            VALUES (SEQ_BOARD.NEXTVAL,#{title},#{content},#{writerNo})
            """)
    int write(BoardVo vo);

    @Select("""
            SELECT B.* , M.NICK AS WRITER_NICK
            FROM BOARD B
            JOIN MEMBER M 0N (B.WRITER_NO = M.NO)
            WHERE B.DEL_YN = 'N'
            ORDER BY B.NO DESC
            """)
    List<BoardVo> selectBoardList();
}
