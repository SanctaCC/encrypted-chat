package com.sanctaultras.encryptedchat.user.register;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("register")
    public ResponseEntity<?>  register(@RequestBody @Valid RegisterForm registerForm, HttpServletRequest req) throws ServletException {
        registerService.register(registerForm);
        return ResponseEntity.status(201).build();
    }
}
