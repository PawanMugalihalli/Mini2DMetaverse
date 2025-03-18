package com.pawan.websockets.dao;

import com.pawan.websockets.entity.PlayerDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerDtoRepository extends JpaRepository<PlayerDto,Integer> {
    public PlayerDto findTopByOrderByIdDesc();
    public List<PlayerDto> findAllByRoomId(String roomId);
}
