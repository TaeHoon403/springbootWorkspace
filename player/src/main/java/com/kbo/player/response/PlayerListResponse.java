package com.kbo.player.response;

import com.kbo.player.PlayerVo;
import lombok.Data;

import java.util.List;

@Data
public class PlayerListResponse {

    private List<PlayerVo> list;

}
