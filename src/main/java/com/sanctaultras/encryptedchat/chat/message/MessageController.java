package com.sanctaultras.encryptedchat.chat.message;

import com.sanctaultras.encryptedchat.user.account.CustomSessionUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("@chatSec.chatRoomAccess(authentication,#chatRoomId)")
    public ResponseEntity<?> postMessage(@PathVariable UUID chatRoomId, @RequestBody Message message,
                                         @AuthenticationPrincipal CustomSessionUser user) {
        Message messag = messageService.addNewMessage(message.getBody(), chatRoomId, user.getId());
        return ResponseEntity.status(201).body(messag);
    }

}
