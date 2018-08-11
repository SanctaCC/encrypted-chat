package com.sanctaultras.encryptedchat.user;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class LoginController {

    //    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping("/login")
    public String successfulLogin(HttpServletResponse res) throws IOException {
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"))) {
            res.sendError(401);
        }
        return "logged in";
    }

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