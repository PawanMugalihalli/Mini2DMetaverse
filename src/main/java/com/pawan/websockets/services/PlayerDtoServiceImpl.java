package com.pawan.websockets.services;

import com.pawan.websockets.dao.PlayerDtoRepository;
import com.pawan.websockets.entity.PlayerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerDtoServiceImpl implements PlayerDtoService{

    @Autowired
    PlayerDtoRepository playerDtoRepository;

    public PlayerDto findLastRow() {
        return playerDtoRepository.findTopByOrderByIdDesc();
    }

    @Override
    public PlayerDto save(PlayerDto playerDto) {
        return playerDtoRepository.save(playerDto);
    }

    @Override
    public List<PlayerDto> findAllByRoomId(String roomId) {
        return playerDtoRepository.findAllByRoomId(roomId);
    }
}
