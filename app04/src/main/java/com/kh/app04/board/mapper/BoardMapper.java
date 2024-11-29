package com.kh.app04.board.mapper;

import com.kh.app04.board.vo.BoardVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardMapper {

    @Insert("""
            INSERT INTO BOARD (NO,TITLE,CONTENT,WRITER_NO)
            VALUES(SEQ_BOARD.NEXTVAL,#{title},#{content},'1')
            """)
    int insert(BoardVo vo);

}
