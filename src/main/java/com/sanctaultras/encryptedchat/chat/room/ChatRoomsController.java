package com.sanctaultras.encryptedchat.chat.room;

import com.sanctaultras.encryptedchat.chat.message.MessageService;
import com.sanctaultras.encryptedchat.user.account.CustomSessionUser;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class ChatRoomsController {

    private final MessageService messageService;

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomsController(MessageService messageService, ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.messageService = messageService;
    }

    @GetMapping("/chatrooms")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getAvailableChatRooms(@AuthenticationPrincipal CustomSessionUser user) {
        List<ChatRoom> list = chatRoomRepository.findAllByUser_Id(user.getId());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/chatrooms/{id}/messages")
    public ResponseEntity<?> getChatRoomMessages(@PathVariable UUID id, Pageable pageable) {
        return ResponseEntity.ok(messageService.getChatRoomMessages(id,pageable));
    }
}
