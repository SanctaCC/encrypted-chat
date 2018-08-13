package com.sanctaultras.encryptedchat.user.chat;

import com.sanctaultras.encryptedchat.user.CustomSessionUser;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatRoomsController {

    private final MessageService messageService;

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomsController(MessageService messageService, ChatRoomRepository chatRoomRepository) {
        this.messageService = messageService;
        this.chatRoomRepository = chatRoomRepository;
    }

    @GetMapping("/chatrooms")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getAvailableChatRooms(@AuthenticationPrincipal CustomSessionUser user) {
        List<ChatRoom> list = chatRoomRepository.findAllByUser_Id(user.getId());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/chatrooms/{id}/messages")
    public ResponseEntity<?> getChatRoomMessages(@PathVariable Long id,Pageable pageable) {
        return ResponseEntity.ok(messageService.getChatRoomMessages(id,pageable));
    }
}
