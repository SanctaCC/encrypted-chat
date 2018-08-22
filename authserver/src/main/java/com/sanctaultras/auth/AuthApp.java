package com.sanctaultras.auth;

import com.sanctaultras.auth.user.User;
import com.sanctaultras.auth.user.UserRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
public class AuthApp {

    public static void main(String... args) {
        SpringApplication.run(AuthApp.class,args);
    }

    @Bean
    public ApplicationRunner runner(UserRepository u) {
        User user = User.builder().email("admin")
                .password("$2a$12$xXkEzvvadsvzuSlrsNDj8e9QvgnIYUeEmQPl/NqVeqc2O0x90h7hO")
                .build();
        return args -> u.save(user);
    }
}
