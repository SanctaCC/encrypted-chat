package com.sanctaultras.encryptedchat.web.security;

import com.sanctaultras.encryptedchat.user.chat.ChatRoomRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("chatSec")
public class ChatRoomAccessMethodSecurityExpression {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomAccessMethodSecurityExpression(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    public boolean chatRoomAccess(Authentication obj, UUID chatRoomId) {
        if(!(obj.getPrincipal() instanceof User))
            return false;
        User user = (User) obj.getPrincipal();
        Integer hasPerm = chatRoomRepository.isParticipating(chatRoomId,user.getUsername());
        return hasPerm != null;
    }
}
