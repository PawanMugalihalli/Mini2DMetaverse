package com.pawan.websockets.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="chatroom")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String roomId;
    @Column(name="roomName", nullable = false)
    private String roomName;
    @Column(name="createdBy",nullable =false)
    private String createdBy;
    @Column(name="isPrivate", nullable= false)
    private String isPrivate;

    public ChatRoom(String roomName,String createdBy,String isPrivate) {
        this.roomId = UUID.randomUUID().toString();
        this.roomName = roomName;
        this.createdBy = createdBy;
        this.isPrivate = isPrivate;
    }


}
