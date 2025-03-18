package com.pawan.websockets.services;

import com.pawan.websockets.entity.PlayerDto;
import com.pawan.websockets.entity.TicTacToe;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TicTacToeService {
    @Transactional
    void save(TicTacToe ticTacToe);
    List<TicTacToe> findAllByRoomId(String roomId);
}
