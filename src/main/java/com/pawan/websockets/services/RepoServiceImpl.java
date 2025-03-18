package com.pawan.websockets.services;

import com.pawan.websockets.dao.ChatRoomRepository;
import com.pawan.websockets.dao.MessageRepository;
import com.pawan.websockets.dao.TicTacToeRepository;
import com.pawan.websockets.dao.UserRepository;
import com.pawan.websockets.entity.ChatRoom;
import com.pawan.websockets.entity.Messages;
import com.pawan.websockets.entity.TicTacToe;
import com.pawan.websockets.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepoServiceImpl implements RepoService{
    @Autowired
    MessageRepository messageRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    ChatRoomRepository chatRoomRepo;
    @Autowired
    TicTacToeRepository ticTacToeRepo;


    public RepoServiceImpl(MessageRepository repo, UserRepository userRepo){
        this.messageRepo=repo;
        this.userRepo=userRepo;
    }

    @Override
    public Optional<Messages> findById(int id) {
        Optional<Messages> message=messageRepo.findById(id);
        return message;
    }

    @Override
    @Transactional
    public void save(Messages msg) {
        messageRepo.save(msg);
    }

    @Override
    @Transactional
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public User findByUsername(String username){
        return userRepo.findByUsername(username);
    }

    @Override
    public List<Messages> getAllMessages() {
        return messageRepo.findAll();
    }

    @Override
    public List<Messages> findAllByRoomId(String roomId) {
        return messageRepo.findAllByRoomId(roomId);
    }

    @Override
    public void save(ChatRoom room) {
       chatRoomRepo.save(room);
    }

    @Override
    public List<ChatRoom> findAll() {
        return chatRoomRepo.findAll();
    }

    public Optional<ChatRoom> findById(String id){
        return chatRoomRepo.findById(id);
    }

    @Override
    public void delete(ChatRoom room) {
        chatRoomRepo.delete(room);
    }


}
