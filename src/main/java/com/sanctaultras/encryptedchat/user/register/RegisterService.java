package com.sanctaultras.encryptedchat.user.register;

import com.sanctaultras.encryptedchat.user.User;
import com.sanctaultras.encryptedchat.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public RegisterService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void register(RegisterForm registerForm) {
        User newUser = new User();
        newUser.setEmail(registerForm.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerForm.getPassword()));
        userRepository.save(newUser);
    }
}
