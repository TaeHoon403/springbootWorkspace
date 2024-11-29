package com.kh.app03.member.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {

    //@Insert("INSERT INTO MEMBER (NO, ID, PW, NICK) VALUES(SEQ_MEMBER.NEXTVAL,#{id},#{pw},#{nick})")
    @Insert("INSERT INTO MEMBER (NO, ID, PW, NICK) VALUES(SEQ_MEMBER.NEXTVAL,'user1104','1234','nick1234')")
    int m01(); // 앞에 public abstract  생략됨

}
