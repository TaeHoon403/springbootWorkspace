package com.kh.SEMI.member.mapper;

import com.kh.SEMI.member.vo.MemberVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MemberMapper {

    @Insert("""
            INSERT INTO MEMBER
            (
                NO
                , ID
                , PWD
                , NICK
                , PROFILE
            )
            VALUES
            (
                SEQ_MEMBER.NEXTVAL
                , #{id}
                , #{pwd}
                , #{nick}
                , #{profile}
            )
            """)
    int join(MemberVo vo);

    @Select("""
            SELECT
                NO
                , ID
                , PWD
                , NICK
                , PROFILE
                , DEL_YN
                , ENROLL_DATE
                , MODIFY_DATE
            FROM MEMBER
            WHERE ID = #{id}
            AND PWD = #{pwd}
            AND DEL_YN = 'N'
            """)
    MemberVo login(MemberVo vo);

//    @Update("""
//            UPDATE MEMBER
//            SET
//                MODIFY_DATE = SYSDATE
//                <if test="pwd != null">
//                    , PWD =#{pwd}
//                </if>
//                <if test="nick != null">
//                    , NICK = #{nick}
//                </if>
//            WHERE NO = #{no}
//            AND DEL_YN = 'N'
//            """)
    int edit(MemberVo vo);

    @Select("""
            SELECT
                NO
                , ID
                , PWD
                , NICK
                , PROFILE
                , DEL_YN
                , ENROLL_DATE
                , MODIFY_DATE
            FROM MEMBER
            WHERE NO = #{no}
            AND DEL_YN = 'N'
            """)
    MemberVo getMemberByNo(String no);

    @Update("""
            UPDATE MEMBER
            SET
                DEL_YN = 'Y'
            WHERE NO = #{no}
            AND PWD = #{pwd}
            """)
    int quit(MemberVo vo);

    @Select("""
            SELECT
                NO
                , ID
                , PWD
                , NICK
                , PROFILE
                , DEL_YN
                , ENROLL_DATE
                , MODIFY_DATE
            FROM MEMBER
            WHERE id = #{id}
            """)
    MemberVo CheckDupId(String id);
}
