package com.sanctaultras.encryptedchat.user.account.register;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public void emailAlreadyTaken(Exception e, HttpServletResponse res) throws IOException {
        res.sendError(HttpStatus.CONFLICT.value(),"Email already taken");
    }

    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody @Valid RegisterForm registerForm) {
        User user = registerService.register(registerForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
