package com.sanctaultras.encryptedchat.user.account;

import com.sanctaultras.encryptedchat.user.User;
import com.sanctaultras.encryptedchat.user.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void changePassword(Long userId,String oldPass,String newPass) {
        User u = userRepository.getOne(userId);
        if (passwordEncoder.matches(oldPass,u.getPassword())) {
            u.setPassword(passwordEncoder.encode(newPass));
        }
        else {
            throw new BadCredentialsException("Bad Credentials");
        }
    }
}
