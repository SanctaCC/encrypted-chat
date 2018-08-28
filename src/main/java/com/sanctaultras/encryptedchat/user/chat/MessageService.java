package com.sanctaultras.encryptedchat.user.chat;

import com.sanctaultras.encryptedchat.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    private final UserRepository userRepository;

    private final ChatRoomRepository chatRoomRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository, ChatRoomRepository chatRoomRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    @PreAuthorize("@chatSec.chatRoomAccess(authentication,#id)")
    public Page<Message> getChatRoomMessages(UUID id, Pageable pageable) {
        return messageRepository.findByParentChatRoom_Id(id,pageable);
    }

    @PreAuthorize("@chatSec.chatRoomAccess(authentication,#chatRoomId)")
    public Message addNewMessage(String body, UUID chatRoomId, Long userId) {
        Message message = new Message();
        message.setBody(body);
        message.setAuthor(userRepository.getOne(userId));
        message.setParentChatRoom(chatRoomRepository.getOne(chatRoomId));
        return messageRepository.save(message);
    }

}
