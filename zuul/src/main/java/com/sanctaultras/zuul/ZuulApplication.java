package com.sanctaultras.zuul;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@Slf4j
@EnableZuulProxy
public class ZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}

//	@Bean
//	public ApplicationRunner applicationRunner(UserRepository userRepository,
//                                               ChatRoomRepository chatRoomRepository) {
//	    User admin = User.builder().email("admin")
//                .password("$2a$12$xXkEzvvadsvzuSlrsNDj8e9QvgnIYUeEmQPl/NqVeqc2O0x90h7hO")
//                .build();
//g
//        User user2 = User.builder().email("admin2").password("password123").build();
//        userRepository.saveAll(Arrays.asList(admin, user2));
//        ChatRoom first = new ChatRoom();
//        first.setName("chat name");
//        first.setUsers(new HashSet<>());
//        first.getUsers().add(admin);
//        first.getUsers().add(user2);
//        return args -> chatRoomRepository.save(first);
//	}   //admin admin
}
