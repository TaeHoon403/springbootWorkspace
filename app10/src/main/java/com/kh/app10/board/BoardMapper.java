package com.kh.app10.board;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardMapper {

    // 게시글 리스트 조회
    @Select("""
            SELECT * 
            FROM BOARD
            ORDER BY NO DESC
            """)
    List<BoardVo> getBoardList();

}//interface
