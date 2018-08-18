package com.sanctaultras.encryptedchat.user.chat;

import com.sanctaultras.encryptedchat.user.User;
import com.sanctaultras.encryptedchat.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public ChatRoomService(ChatRoomRepository chatRoomRepository,
                           UserRepository userRepository
    ) {
        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
    }

    public void createNewChatRoom(Long userId, Set<String> users) {
//        Set<User> entityUsers = users.stream().map(userRepository::getOne).collect(Collectors.toSet());
        Set<User> entityUsers = users.stream().map(User::new).collect(Collectors.toSet());
        ChatRoom chR = new ChatRoom();
        chR.setUsers(entityUsers);
        chatRoomRepository.save(chR);
    }
}
