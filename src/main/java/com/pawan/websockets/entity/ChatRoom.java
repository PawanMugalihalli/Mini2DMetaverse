package com.pawan.websockets.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    @Column(name="roomId",unique = true,nullable = false)
    private String roomId= UUID.randomUUID().toString();
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
