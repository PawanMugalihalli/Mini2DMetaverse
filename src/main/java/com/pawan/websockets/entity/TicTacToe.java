package com.pawan.websockets.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="TicTacToe")
public class TicTacToe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="username",nullable = false)
    private String username;
    @Column(name="position",nullable = false)
    private int position;
    @Column(name="move")
    private String move;
    @Column(name="roomId",nullable = false)
    private String roomId;

    public TicTacToe(String username, int i, String move, String roomId) {
        this.username=username;
        this.position=i;
        this.move=move;
        this.roomId=roomId;
    }
}
