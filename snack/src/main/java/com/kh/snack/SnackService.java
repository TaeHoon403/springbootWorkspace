package com.kh.snack;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SnackService {

    private final SnackMapper mapper;

    // 신규 과자 등록
    public int enrollSnack(SnackVo vo) {
        return mapper.insert(vo);
    }//enrollSnack

    // 과자 목록 조회
    public List<SnackVo> getSnackList() {
        return mapper.getSnackList();
    }//getSnackList

    // 과자 상세 조회
    public SnackVo getSnackByNo(Long no) {
        return mapper.getSnackByNo(no);
    }//getSnackByNo

    // 과자 삭제
    public int deleteSnack(Long no) {
        return mapper.deleteSnack(no);
    }//deleteSnack

}//class
