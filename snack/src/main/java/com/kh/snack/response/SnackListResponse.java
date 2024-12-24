package com.kh.snack.response;

import com.kh.snack.SnackVo;
import lombok.Data;

import java.util.List;

@Data
public class SnackListResponse {

    private List<SnackVo> list;

}
