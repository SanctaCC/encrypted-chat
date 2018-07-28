package com.sanctaultras.encryptedchat.user;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

@RestController
public class LoginController {

    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void failLoginAttempt(Exception e) {

    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAttempt(@RequestBody LoginForm login) {
         UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                 new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword());
        Authentication auth = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return ResponseEntity.ok(RequestContextHolder.currentRequestAttributes().getSessionId());
    }

}
@Data
class LoginForm {
    private String email;
    private String password;
}
