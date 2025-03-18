package com.pawan.websockets.dao;

import com.pawan.websockets.entity.PlayerDto;
import com.pawan.websockets.entity.TicTacToe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicTacToeRepository extends JpaRepository<TicTacToe,Integer> {
    List<TicTacToe> findAllByRoomId(String roomId);
}
