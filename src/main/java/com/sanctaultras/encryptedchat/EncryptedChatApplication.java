package com.sanctaultras.encryptedchat;

import com.sanctaultras.encryptedchat.user.User;
import com.sanctaultras.encryptedchat.user.UserRepository;
import com.sanctaultras.encryptedchat.user.chat.ChatRoom;
import com.sanctaultras.encryptedchat.user.chat.ChatRoomRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;

@SpringBootApplication
public class EncryptedChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncryptedChatApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(UserRepository userRepository,
                                               ChatRoomRepository chatRoomRepository) {
	    User admin = User.builder().email("admin")
                .password("$2a$12$xXkEzvvadsvzuSlrsNDj8e9QvgnIYUeEmQPl/NqVeqc2O0x90h7hO")
                .build();
        ChatRoom first = new ChatRoom();
        first.setName("chat name");
        first = chatRoomRepository.save(first);
        admin.setUserRooms(new HashSet<>());
        admin.getUserRooms().add(first);
		return args -> userRepository.save(admin);
	}   //admin admin
}
