package com.pawan.websockets.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="playerdto")
public class PlayerDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String player1;
    private String player2;
    private String roomId;

    public PlayerDto(String player1, String player2, String roomId) {
        this.player1=player1;
        this.player2=player2;
        this.roomId=roomId;
    }
}
