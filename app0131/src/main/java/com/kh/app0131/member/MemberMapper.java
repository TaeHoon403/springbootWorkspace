package com.kh.app0131.member;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {

    // join
    @Insert("""
            INSERT INTO MEMBER
            (
                NO
                , ID
                , PWD
                , NICK
            )
            VALUES
            (
                SEQ_MEMBER.NEXTVAL
                , #{id}
                , #{pwd}
                , #{nick}
            )
            """)
    public int join(MemberVo vo);

    @Select("""
            SELECT *
            FROM MEMBER
            WHERE ID = #{id}
                AND DEL_YN = 'N' 
            """)
    MemberVo findById(String id);
}
