package com.pawan.websockets.dao;

import com.pawan.websockets.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,Integer> {
    Optional<ChatRoom> findByRoomId(String id);
}
