package com.pawan.websockets.controllers;

import com.pawan.websockets.chat.ChatMessage;
import com.pawan.websockets.chat.MessageType;
import com.pawan.websockets.entity.Messages;
import com.pawan.websockets.entity.PlayerDto;
import com.pawan.websockets.entity.TicTacToe;
import com.pawan.websockets.services.PlayerDtoService;
import com.pawan.websockets.services.RepoService;
import com.pawan.websockets.services.TicTacToeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.pawan.websockets.chat.MessageType.JOIN;
import static com.pawan.websockets.chat.MessageType.PREV;

@Controller
public class ChatController {


    @Autowired
    RepoService repoService;
    @Autowired
    TicTacToeService ticTacToeService;
    @Autowired
    PlayerDtoService playerDtoService;

    @MessageMapping("/chat/{room_id}/sendMessage")
    @SendTo("/topic/messages/{room_id}")
    public ChatMessage sendMessage(@DestinationVariable String room_id, @Payload ChatMessage chatMessage) {

        System.out.println("📩 Message Received from " + chatMessage.getSender() + ": " + chatMessage.getContent());

        try {
            Messages msg = new Messages(chatMessage.getSender(), chatMessage.getContent(),room_id);
            msg.setTimeStamp(LocalDateTime.now());
            MessageType type=chatMessage.getType();
            if(type!=PREV && type!=JOIN) {
                System.out.println("hi");
                repoService.save(msg);
            }
        } catch (Exception e) {
            System.err.println("❌ Error saving message: " + e.getMessage());
        }

        return chatMessage;
    }

    @MessageMapping("/chat/{room_id}/sendMoveMessage")
    @SendTo("/topic/moves/{room_id}")
    public TicTacToe sendMoveMessage(@Payload TicTacToe ticTacToe){
        System.out.println("📩 Message Received from " + ticTacToe.getUsername() + ": " + ticTacToe.getMove());

        try {
            ticTacToeService.save(ticTacToe);
            System.out.println("Move saved successfully!");
        } catch (Exception e) {
            System.err.println("❌ Error saving the move: " + e.getMessage());
        }

        return ticTacToe;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/messages/{room_id}")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, StompHeaderAccessor headerAccessor) {
        System.out.println("✅ User Joined: " + chatMessage.getSender());
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", chatMessage.getSender());
        return chatMessage;
    }

    @MessageMapping("/chat/{room_id}/players")
    @SendTo("/topic/players/{room_id}")
    public PlayerDto addPlayer(@DestinationVariable String room_id, @Payload PlayerDto playerDto){
        TicTacToe ticTacToe=new TicTacToe("username",0,"start",room_id);
        ticTacToeService.save(ticTacToe);
        PlayerDto playerDto1=new PlayerDto(playerDto.getPlayer1(), playerDto.getPlayer2(),room_id);
        playerDtoService.save(playerDto1);
        System.out.println("Player received");
        return playerDto;
    }
}
