package com.sanctaultras.encryptedchat;

import com.sanctaultras.encryptedchat.user.User;
import com.sanctaultras.encryptedchat.user.UserRepository;
import com.sanctaultras.encryptedchat.user.chat.ChatRoom;
import com.sanctaultras.encryptedchat.user.chat.ChatRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.util.HashSet;

@SpringBootApplication
@Slf4j
@EnableEurekaClient
@EnableRedisHttpSession
public class EncryptedChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncryptedChatApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(UserRepository userRepository,
											   ChatRoomRepository chatRoomRepository) {
		User u = new User("admin");
		userRepository.save(u);
        ChatRoom first = new ChatRoom();
        first.setName("chat name");
        first.setUsers(new HashSet<>());
        first.getUsers().add(u);
        return args -> chatRoomRepository.save(first);
	}
}
