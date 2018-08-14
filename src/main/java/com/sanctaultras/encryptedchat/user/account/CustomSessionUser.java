package com.sanctaultras.encryptedchat.user.account;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@JsonIgnoreProperties({"authorities","password"})
public class CustomSessionUser extends User {

    public CustomSessionUser(String username, String password, Collection<? extends GrantedAuthority> authorities,
                             Long id) {
        super(username, password, authorities);
        this.id = id;
    }

    private Long id;
}

