package com.sanctaultras.encryptedchat.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

//    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/login")
    public void successfulLogin() {
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @RequestMapping("/failed-login")
    public void failedLogin() {
    }

//    @PreAuthorize("isAuthenticated()")
    @RequestMapping("authenticated")
    public String authenticated() {
        return "OK";
    }

//    @PreAuthorize("isAnonymous()")
    @RequestMapping("anonymous")
    public String anonymous() {
        return "OK";
    }

}