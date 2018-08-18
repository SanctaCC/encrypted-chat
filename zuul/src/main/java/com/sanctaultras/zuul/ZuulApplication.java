package com.sanctaultras.zuul;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@Slf4j
@EnableZuulProxy
@EnableEurekaServer
@EnableFeignClients
public class ZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
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
