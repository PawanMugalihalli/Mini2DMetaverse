package com.pawan.websockets.dao;

import com.pawan.websockets.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom,String> {
}
