package com.pawan.websockets.services;

import com.pawan.websockets.entity.ChatRoom;
import com.pawan.websockets.entity.Messages;
import com.pawan.websockets.entity.TicTacToe;
import com.pawan.websockets.entity.User;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface RepoService {

    public Optional<Messages> findById(int id);

    @Transactional
    void save(Messages msg);

    @Transactional
    void save(User user);

    User findByUsername(String username);

    List<Messages> getAllMessages();

    List<Messages> findAllByRoomId(String roomId);

    @Transactional
    void save(ChatRoom room);

    List<ChatRoom> findAll();

    public Optional<ChatRoom> findById(String id);

    void delete(ChatRoom room);



}
