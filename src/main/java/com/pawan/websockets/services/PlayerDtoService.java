package com.pawan.websockets.services;

import com.pawan.websockets.entity.PlayerDto;

import java.util.List;

public interface PlayerDtoService {
    public PlayerDto findLastRow();

    PlayerDto save(PlayerDto playerDto);
    List<PlayerDto> findAllByRoomId(String  roomId);
}
