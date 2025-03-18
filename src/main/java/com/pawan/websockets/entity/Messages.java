package com.pawan.websockets.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="messages")
public class Messages {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;
    @Column(name="username")
    private String Username;

    @Column(name="message")
    private String Message;

    @Column(name="time_stamp")
    private LocalDateTime TimeStamp;

    @Column(name="room_id")
    private String roomId;

    public Messages(String username, String message, String roomId){
        Username=username;
        Message=message;
        this.roomId=roomId;
    }
}
