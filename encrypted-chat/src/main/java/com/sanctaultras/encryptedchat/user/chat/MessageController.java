package com.sanctaultras.encryptedchat.user.chat;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/chatrooms/{chatRoomId}/messages")
    public ResponseEntity<?> postMessage(@PathVariable UUID chatRoomId, @RequestBody Message message,
                                         @AuthenticationPrincipal User user) {
        Message messag = messageService.addNewMessage(message.getBody(),chatRoomId,user.getUsername());
        Map map = new HashMap<>();
        map.put("id",messag.getId());
        map.put("body",messag.getBody());
        map.put("author",user.getUsername());
        return ResponseEntity.status(201).body(map);
    }

}
