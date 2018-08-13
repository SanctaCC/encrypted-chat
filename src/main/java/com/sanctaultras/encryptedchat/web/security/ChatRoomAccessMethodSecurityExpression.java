package com.sanctaultras.encryptedchat.web.security;

import com.sanctaultras.encryptedchat.user.CustomSessionUser;
import com.sanctaultras.encryptedchat.user.chat.ChatRoomRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("chatSec")
public class ChatRoomAccessMethodSecurityExpression {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomAccessMethodSecurityExpression(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    public boolean chatRoomAccess(Authentication obj, Long chatRoomId) {
        if(!(obj.getPrincipal() instanceof CustomSessionUser))
            return false;
        CustomSessionUser user = (CustomSessionUser) obj.getPrincipal();
        Integer hasPerm = chatRoomRepository.isParticipating(chatRoomId,user.getId());
        return hasPerm != null;
    }
}
