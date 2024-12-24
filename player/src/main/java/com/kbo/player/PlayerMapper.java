package com.kbo.player;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PlayerMapper {
    
    // 신규 선수 등록
    @Insert("""
            INSERT INTO PLAYER
            (
                NO
                , NAME
                , TEAM
                , POSITION
                , BACK_NUMBER
                , DEBUT_DATE
            )
            VALUES
            (
                SEQ_PLAYER.NEXTVAL
                , #{name}
                , #{team}
                , #{position}
                , #{backNumber}
                , #{debutDate}
            )
            """)
    int insertPlayer(PlayerVo vo);

    // 선수 목록 조회
    @Select("""
            SELECT *
            FROM PLAYER
            WHERE RETIRED_YN = 'N'
            ORDER BY TEAM , NAME
            """)
    List<PlayerVo> getPlayerList();

    // 선수 상세 조회
    @Select("""
            SELECT *
            FROM PLAYER
            WHERE NO = #{no}
            AND RETIRED_YN = 'N'
            """)
    PlayerVo getPlayerInfo(Long no);

    // 선수 정보 삭제
    @Delete("""
            DELETE FROM PLAYER
            WHERE NO = #{no}
            """)
    int deletePlayer(Long no);
    
    // 선수 정보 수정
    int editPlayerInfo(PlayerVo vo);
    
}//interface
