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

    private final ChatRoomRepository chatRoomRepository;

    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository,
                          ChatRoomRepository chatRoomRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
    }

    @PreAuthorize("@chatSec.chatRoomAccess(authentication,#id)")
    public Page<Message> getChatRoomMessages(UUID id, Pageable pageable) {
        return messageRepository.findByParentChatRoom_Id(id,pageable);
    }

    @PreAuthorize("@chatSec.chatRoomAccess(authentication,#chatRoomId)")
    public Message addNewMessage(String body, UUID chatRoomId, String userId) {
        Message message = new Message();
        message.setBody(body);
        message.setAuthor(userRepository.getOne(userId));
        message.setParentChatRoom(chatRoomRepository.getOne(chatRoomId));
        return messageRepository.save(message);
    }

}
