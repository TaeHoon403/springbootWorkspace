package com.kh.prac11.board;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Select("""
            SELECT *
            FROM BOARD
            ORDER BY NO DESC
            """)
    List<BoardVo> getBoardList();

    @Insert("""
            INSERT INTO BOARD
            (
                NO
                , TITLE
                , WRITER
            )
            VALUES
            (
                SEQ_BOARD.NEXTVAL
                , #{title}
                , #{writer}
            )
            """)
    int insertBoard(BoardVo vo);

    @Select("""
            SELECT *
            FROM BOARD
            WHERE NO = #{no}
            """)
    BoardVo getBoardByNo(Long no);

}//interface
