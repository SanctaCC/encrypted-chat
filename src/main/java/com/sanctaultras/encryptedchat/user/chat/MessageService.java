package com.sanctaultras.encryptedchat.user.chat;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @PreAuthorize("@chatSec.chatRoomAccess(authentication,#id)")
    public Page<Message> getChatRoomMessages(Long id, Pageable pageable) {
        return messageRepository.findByParentChatRoom_Id(id,pageable);
    }
}
