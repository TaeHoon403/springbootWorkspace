package com.kh.app06.member.mapper;

import com.kh.app06.member.vo.MemberVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {

    @Insert("""
            INSERT INTO MEMBER(NO, ID, PW, NICK)
            VALUES(SEQ_MEMBER.NEXTVAL,#{id},#{pw},#{nick})
            """)
    int join(MemberVo vo);

    @Select("""
            SELECT * FROM MEMBER
            WHERE ID = #{id} AND PW = #{pw} AND DEL_YN = 'N'
            """)
    MemberVo login(MemberVo vo);

}
