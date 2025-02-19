package com.kh.app_final.admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    @Select("""
            SELECT 
                NO
                ,ID
                ,PWD
                ,NICK
                ,ENROLL_DATE
                ,MODIFY_DATE
                ,DEL_YN
            FROM ADMIN
            WHERE ID = #{id}
            AND DEL_YN = 'N'
            """)
    AdminVo findAdminById(String id);

}//class
