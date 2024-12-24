package com.kh.snack;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SnackMapper {

    // 신규 과자 등록
    @Insert("""
            INSERT INTO SNACK
            (
                NO
                , NAME
                , PRICE
                , BRAND
                , EXPIRATION_DATE
            )
            VALUES
            (
                SEQ_SNACK.NEXTVAL
                , #{name}
                , #{price}
                , #{brand}
                , #{expirationDate}
            )
            """)
    int insert(SnackVo vo);

    // 과자 목록 조회
    @Select("""
            SELECT *
            FROM SNACK
            ORDER BY NO DESC
            """)
    List<SnackVo> getSnackList();

    // 과자 상세 조회
    @Select("""
            SELECT *
            FROM SNACK
            WHERE NO = #{no}
            """)
    SnackVo getSnackByNo(Long no);

    // 과자 삭제
    @Delete("""
            DELETE FROM SNACK
            WHERE NO = #{no}
            """)
    int deleteSnack(Long no);

}//interface
