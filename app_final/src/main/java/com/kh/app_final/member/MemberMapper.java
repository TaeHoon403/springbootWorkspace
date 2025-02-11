package com.kh.app_final.member;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {

    // 회원가입
    @Insert("""
            INSERT INTO MEMBER
            (
                ID
                , PWD
                , NICK
            )
            VALUES
            (
                #{id}
                , #{pwd}
                , #{nick}
            )
            """)
    int join(MemberVo vo);

    // 회원정보 조회 by ID
    @Select("""
            SELECT
            NO
            , ID
            , PWD
            , NICK
            , ENROLL_DATE
            , MODIFY_DATE
            , DEL_YN
            FROM MEMBER
            WHERE ID = #{id}
            AND DEL_YN = 'N'
            """)
    MemberVo findUserById(String id);

}//class
