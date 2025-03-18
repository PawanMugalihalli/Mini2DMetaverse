package com.pawan.websockets.services;

import com.pawan.websockets.dao.PlayerDtoRepository;
import com.pawan.websockets.dao.TicTacToeRepository;
import com.pawan.websockets.entity.PlayerDto;
import com.pawan.websockets.entity.TicTacToe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicTacToeServiceImpl implements TicTacToeService{

    @Autowired
    TicTacToeRepository ticTacToeRepo;
    @Autowired
    PlayerDtoRepository playerDtoRepo;
    public void save(TicTacToe ticTacToe){
        if (ticTacToe.getPosition() < 0 || ticTacToe.getPosition() > 8) {
            throw new IllegalArgumentException("Invalid move: Out of bounds.");
        }
        ticTacToeRepo.save(ticTacToe);
    }
    public List<TicTacToe> findAllByRoomId(String roomId){
        return ticTacToeRepo.findAllByRoomId(roomId);
    }

}
