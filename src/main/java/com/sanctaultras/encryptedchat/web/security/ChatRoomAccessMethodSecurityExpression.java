package com.sanctaultras.encryptedchat.web.security;

import com.sanctaultras.encryptedchat.chat.room.ChatRoomRepository;
import com.sanctaultras.encryptedchat.user.account.CustomSessionUser;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("chatSec")
public class ChatRoomAccessMethodSecurityExpression {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomAccessMethodSecurityExpression(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    public boolean chatRoomAccess(Authentication obj, UUID chatRoomId) {
        if(!(obj.getPrincipal() instanceof CustomSessionUser))
            return false;
        CustomSessionUser user = (CustomSessionUser) obj.getPrincipal();
        Integer hasPerm = chatRoomRepository.isParticipating(chatRoomId, user.getId());
        return hasPerm != null;
    }
}