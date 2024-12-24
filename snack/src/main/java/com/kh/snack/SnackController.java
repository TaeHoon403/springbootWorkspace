package com.kh.snack;

import com.kh.snack.response.SnackDeleteResponse;
import com.kh.snack.response.SnackDetailResponse;
import com.kh.snack.response.SnackEnrollResponse;
import com.kh.snack.response.SnackListResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // controller + responsebody
@RequestMapping("snack")
@RequiredArgsConstructor
@Slf4j
public class SnackController {

    private final SnackService service;

    // 신규 과자 등록
    @PostMapping
    public SnackEnrollResponse enrollSnack(SnackVo vo){

        int result = service.enrollSnack(vo);

        SnackEnrollResponse response = new SnackEnrollResponse();
        response.setResult(result);

        return response;

    }//enrollSnack

    // 과자 목록 조회
    @GetMapping("list")
    public SnackListResponse getSnackList(){

        List<SnackVo> snackList = service.getSnackList();

        SnackListResponse response = new SnackListResponse();
        response.setList(snackList);

        return response;

    }//getSnackList

    // 과자 상세 조회
    @GetMapping("{no}")
    public SnackDetailResponse getSnackByNo(@PathVariable Long no){

        SnackVo vo = service.getSnackByNo(no);

        SnackDetailResponse response = new SnackDetailResponse();
        response.setData(vo);

        return response;

    }//getSnackByNo

    // 과자 삭제
    @DeleteMapping("{no}")
    public SnackDeleteResponse deleteSnack(@PathVariable Long no){

        int result = service.deleteSnack(no);

        SnackDeleteResponse response = new SnackDeleteResponse();
        response.setResult(result);

        return response;

    }//deleteSnack

}//class
