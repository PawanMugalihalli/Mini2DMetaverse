package com.pawan.websockets.controllers;

import com.pawan.websockets.entity.ChatRoom;
import com.pawan.websockets.entity.PlayerDto;
import com.pawan.websockets.services.RepoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    RepoService repoService;
    @PostMapping("/chat-lobby/create")
    public void create(HttpSession session, @RequestBody ChatRoom roomRequest) {
        System.out.println(roomRequest.getCreatedBy());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            String roomId = UUID.randomUUID().toString();
            roomRequest.setCreatedBy(username);
            roomRequest.setRoomId(roomId);
            repoService.save(roomRequest);
        }
    }



    @DeleteMapping("/chat-lobby/delete/{room_id}")
    public void delete(HttpSession session, @PathVariable String room_id){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null && authentication.isAuthenticated()){
            String username;
            username=authentication.getName();
            Optional<ChatRoom> chatRoom=repoService.findByRoomId(room_id);
            String createdBy=chatRoom.get().getCreatedBy();
            if(createdBy.equals(username)){
                repoService.delete(chatRoom.get());
            }
        }
    }

    @GetMapping("/current-user")
    public String currentUser(HttpSession session){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/chat-lobby/rooms")
    public List<ChatRoom> showRooms(){
        return repoService.findAll();
    }

    @PostMapping("chatBox/{roomId}/players")
    public ResponseEntity<PlayerDto> setPlayers(@RequestBody PlayerDto playerDto) {
        System.out.println("Received players: " + playerDto.getPlayer1() + " and " + playerDto.getPlayer2());
        return ResponseEntity.ok(playerDto);
    }
}
