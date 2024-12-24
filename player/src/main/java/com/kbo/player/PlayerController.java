package com.kbo.player;

import com.kbo.player.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("player")
@RequiredArgsConstructor
@Slf4j
public class PlayerController {

    private final PlayerService service;

    // 신규 선수 등록
    @PostMapping
    public PlayerAddResponse insertPlayer(PlayerVo vo){

        int result = service.insertPlayer(vo);

        PlayerAddResponse response = new PlayerAddResponse();
        response.setResult(result);

        return response;

    }//insertPlayer

    // 선수 목록 조회
    @GetMapping("list")
    public PlayerListResponse getPlayerList(){

        List<PlayerVo> playerVoList = service.getPlayerList();

        PlayerListResponse response = new PlayerListResponse();
        response.setList(playerVoList);

        return response;

    }//getPlayerList
    
    // 선수 상세 조회
    @GetMapping("{no}")
    public PlayerDataResponse getPlayerInfo(@PathVariable Long no){

        PlayerVo playerVo = service.getPlayerInfo(no);

        PlayerDataResponse response = new PlayerDataResponse();
        response.setData(playerVo);

        return response;

    }//getPlayerInfo

    // 선수 정보 삭제
    @DeleteMapping("{no}")
    public PlayerDeleteResponse deletePlayer(@PathVariable Long no){

        int result = service.deletePlayer(no);

        PlayerDeleteResponse response = new PlayerDeleteResponse();
        response.setResult(result);

        return response;

    }//deletePlayer

    // 선수 정보 수정
    @PostMapping("edit")
    public PlayerEditResponse editPlayerInfo(PlayerVo vo){
        
        int result = service.editPlayerInfo(vo);

        PlayerEditResponse response = new PlayerEditResponse();
        response.setResult(result);

        return response;

    }//editPlayerInfo

}//class
