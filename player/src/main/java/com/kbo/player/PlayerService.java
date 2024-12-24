package com.kbo.player;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PlayerService {

    private final PlayerMapper mapper;

    // 신규 선수 등록
    public int insertPlayer(PlayerVo vo) {
        return mapper.insertPlayer(vo);
    }//insertPlayer

    // 선수 목록 조회
    public List<PlayerVo> getPlayerList() {
        return mapper.getPlayerList();
    }//getPlayerList

    // 선수 상세 조회
    public PlayerVo getPlayerInfo(Long no) {
        return mapper.getPlayerInfo(no);
    }//getPlayerInfo

    // 선수 정보 삭제
    public int deletePlayer(Long no) {
        return mapper.deletePlayer(no);
    }//deletePlayer
    // 선수 정보 수정
    public int editPlayerInfo(PlayerVo vo) {
        return mapper.editPlayerInfo(vo);
    }//editPlayerInfo

}//class
