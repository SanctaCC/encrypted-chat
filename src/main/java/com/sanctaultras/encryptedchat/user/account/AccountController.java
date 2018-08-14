package com.sanctaultras.encryptedchat.user.account;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/account/password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordForm changePasswordForm, @AuthenticationPrincipal
                                            CustomSessionUser user) {
        accountService.changePassword(user.getId(),changePasswordForm.getOldPassword(),changePasswordForm.getNewPassword());
        return ResponseEntity.ok().build();
    }
}
@Data
class ChangePasswordForm {
    private String oldPassword;
    private String newPassword;
}
