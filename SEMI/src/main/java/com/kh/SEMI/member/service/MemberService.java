package com.kh.SEMI.member.service;

import com.kh.SEMI.member.mapper.MemberMapper;
import com.kh.SEMI.member.vo.MemberVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService {

    private final MemberMapper mapper;

    public int join(MemberVo vo) throws Exception {

        // 비즈니스 로직(유효성 검사)
        validateMemberVo(vo);

        return mapper.join(vo);

    }//join

    public MemberVo login(MemberVo vo) throws Exception {

        // 비즈니스 로직(유효성 검사)
        validateMemberVo(vo);

        return mapper.login(vo);

    }//login

    public MemberVo edit(MemberVo vo) throws Exception {

        // 비즈니스 로직(유효성 검사)
        validateMemberVo(vo);
    
        // 계정정보 업데이트
        int result = mapper.edit(vo);

        // 예외 처리
        if(result != 1){
            String errorMsg = "[MEMBER-E-002] 회원정보 수정 중 오류가 발생했습니다.";
            log.warn(errorMsg);
            throw new Exception(errorMsg);
        }

        // 업데이트 된 계정정보 받아오기
        MemberVo updateInfo =  mapper.getMemberByNo(vo.getNo());

        // 예외 처리
        if(updateInfo == null){
            String errorMsg = "[MEMBER-E-003] 변경 된 회원정보 확인 중 오류가 발생했습니다.";
            log.warn(errorMsg);
            throw new Exception(errorMsg);
        }

        return updateInfo;

    }//edit

    public int quit(MemberVo vo) throws Exception {

        // 비즈니스 로직(유효성 검사)
        validateMemberVo(vo);

        return mapper.quit(vo);

    }//quit

    private void validateMemberVo(MemberVo vo) throws Exception {

        // 오류 메세지 변수 생성
        String errorMsg = null;

        // 아이디 유효성 검사
        if(vo.getId() != null && vo.getId().length() < 4){
            errorMsg = "[MEMBER-V-001] 아이디가 너무 짧습니다.";
        }

        // 비밀번호 유효성 검사
        if(vo.getPwd() != null && vo.getPwd().length() < 4){
            errorMsg = "[MEMBER-V-002] 비밀번호가 너무 짧습니다.";
        }

        // 닉네임 유효성 검사
        if(vo.getNick() != null && vo.getNick().length() < 4){
            errorMsg = "[MEMBER-V-003] 닉네임이 너무 짧습니다.";
        }

        // 유효성 검사 미통과 시 예외 처리
        if(errorMsg != null){
            log.warn(errorMsg);
            throw new Exception(errorMsg);
        }

    }//validate

    public boolean checkDupId(String id) {

        MemberVo vo = mapper.CheckDupId(id);

        return vo != null;

    }//checkDupId

}//class
