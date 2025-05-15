package com.pawan.websockets.controllers;

import com.pawan.websockets.dao.ChatRoomRepository;
import com.pawan.websockets.dao.PlayerDtoRepository;
import com.pawan.websockets.entity.ChatRoom;
import com.pawan.websockets.entity.Messages;
import com.pawan.websockets.entity.PlayerDto;
import com.pawan.websockets.entity.TicTacToe;
import com.pawan.websockets.services.PlayerDtoService;
import com.pawan.websockets.services.RepoService;
import com.pawan.websockets.services.TicTacToeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Controller
public class JspController {

    @Autowired
    RepoService repoService;
    @Autowired
    TicTacToeService ticTacToeService;
    @Autowired
    PlayerDtoService playerDtoService;

    @GetMapping({"/","home"})
    public String Home() {
        return "index";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }

    @GetMapping("/chat-lobby")
    public String chatLobby(){
        return "chat-lobby";
    }
    @GetMapping("chatBox/{roomId}")
    public String chatBox(@PathVariable String roomId, HttpSession session, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            String username = authentication.getName();
            session.setAttribute("loggedInUser", username);  // Store in session
            model.addAttribute("username", username);        // Inject into the model
        }
        List<Messages> messages=repoService.findAllByRoomId(roomId);
        List<TicTacToe> ticTacToes=ticTacToeService.findAllByRoomId(roomId);
        List<PlayerDto> players= playerDtoService.findAllByRoomId(roomId);
        Optional<ChatRoom> chatRoom=repoService.findByRoomId(roomId);
        String createdBy=chatRoom.get().getCreatedBy();
        model.addAttribute("players",players);
        model.addAttribute("ticTacToes",ticTacToes);
        model.addAttribute("messages",messages);
        model.addAttribute("roomId",roomId);
        model.addAttribute("createdBy",createdBy);
        return "chat"; // Redirect to chat page
    }

}
