package com.sanctaultras.encryptedchat;

import com.sanctaultras.encryptedchat.user.User;
import com.sanctaultras.encryptedchat.user.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EncryptedChatApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncryptedChatApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(UserRepository userRepository) {
		return args -> userRepository.save(User.builder().email("admin")
		.password("$2a$12$xXkEzvvadsvzuSlrsNDj8e9QvgnIYUeEmQPl/NqVeqc2O0x90h7hO").build());
	}   //admin admin
}
