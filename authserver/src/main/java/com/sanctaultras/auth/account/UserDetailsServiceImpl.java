package com.sanctaultras.auth.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sanctaultras.auth.user.User;
import com.sanctaultras.auth.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findOneByEmail(username).map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public org.springframework.security.core.userdetails.User createUserDetails(User user) {
        return new
                org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),Collections.emptyList());
    }

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.mixIn(org.springframework.security.core.userdetails.User.class,JacksonUserConfig.class);
        return builder;
    }
    @JsonIgnoreProperties({"authorities","password"})
    private class JacksonUserConfig {}

}