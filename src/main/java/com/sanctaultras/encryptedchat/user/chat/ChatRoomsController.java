package com.sanctaultras.encryptedchat.user.chat;

import com.sanctaultras.encryptedchat.user.CustomSessionUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatRoomsController {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomsController(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    @GetMapping("/chatrooms")
    public ResponseEntity<?> getAvailableChatRooms(@AuthenticationPrincipal CustomSessionUser user) {
        List<ChatRoom> list = chatRoomRepository.findAllByUser_Id(user.getId());
        return ResponseEntity.ok(list);
    }
}
