package com.sanctaultras.encryptedchat.user.chat;

import com.sanctaultras.encryptedchat.user.CustomSessionUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/chatrooms/{chatRoomId}/messages")
    public ResponseEntity<?> postMessage(@PathVariable UUID chatRoomId, @RequestBody Message message,
                                         @AuthenticationPrincipal CustomSessionUser user) {
        return ResponseEntity.status(201).body(messageService.addNewMessage(message.getBody(),chatRoomId,user.getId()));
    }

}
