package com.pawan.websockets.dao;

import com.pawan.websockets.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Messages,Integer> {
    List<Messages>  findAllByRoomId(String roomId);
}
