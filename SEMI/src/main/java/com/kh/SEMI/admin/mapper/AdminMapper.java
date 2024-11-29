package com.kh.SEMI.admin.mapper;

import com.kh.SEMI.admin.vo.AdminVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    // 관리자 로그인
    @Select("""
            SELECT *
            FROM ADMIN
            WHERE ID = #{id}
            AND PWD = #{pwd}
            AND DEL_YN = 'N'
            """)
    AdminVo getAdminVoByIdAndPwd(AdminVo vo);

}//class
