package com.sanctaultras.encryptedchat.user.account;

import com.sanctaultras.encryptedchat.user.UserRepository;
import lombok.Data;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {

    private final AccountService accountService;
    private final UserRepository userRepository;
    private final UserDetailsServiceImpl userDetailsService;

    public AccountController(AccountService accountService, UserRepository userRepository, UserDetailsServiceImpl userDetailsService) {
        this.accountService = accountService;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/account/password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordForm changePasswordForm, @AuthenticationPrincipal
                                            CustomSessionUser user) {

        accountService.changePassword(user.getId(),changePasswordForm.getOldPassword(),changePasswordForm.getNewPassword());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/accounts")
    public ResponseEntity<?> getUsers(Pageable pageable) {
        return ResponseEntity.ok(userRepository.findAll(pageable).map(userDetailsService::createUserDetails));
    }


}
@Data
class ChangePasswordForm {
    private String oldPassword;
    @Size(min=6,max = 72)
    private String newPassword;
}
