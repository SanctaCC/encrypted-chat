package com.sanctaultras.encryptedchat.user.account;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @RequestMapping("/access-denied")
    public String failedLogin() {
        return "login failed";
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping("authenticated")
    public String authenticated() {
        return "OK";
    }

    @PreAuthorize("isAnonymous()")
    @RequestMapping("anonymous")
    public String anonymous() {
        return "OK";
    }

}