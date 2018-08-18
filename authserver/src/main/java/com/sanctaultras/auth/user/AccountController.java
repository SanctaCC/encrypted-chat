package com.sanctaultras.auth.user;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/account/password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordForm changePasswordForm, @AuthenticationPrincipal
            User user) {

        accountService.changePassword(user.getUsername(),changePasswordForm.getOldPassword(),changePasswordForm.getNewPassword());
        return ResponseEntity.ok().build();
    }
}

@Data
class ChangePasswordForm {
    private String oldPassword;
    @Size(min=6,max = 72)
    private String newPassword;
}
