package com.sanctaultras.auth.user;

import com.querydsl.core.types.Predicate;
import com.sanctaultras.auth.account.UserDetailsServiceImpl;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
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
    private final UserDetailsServiceImpl userDetailsService;
    private final UserRepository userRepository;

    public AccountController(AccountService accountService, UserDetailsServiceImpl userDetailsService, UserRepository userRepository) {
        this.accountService = accountService;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    @PostMapping("/account/password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordForm changePasswordForm, @AuthenticationPrincipal
            User user) {

        accountService.changePassword(user.getUsername(),changePasswordForm.getOldPassword(),changePasswordForm.getNewPassword());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/accounts")
    public ResponseEntity<Page<User>> getUsers(@QuerydslPredicate(root = com.sanctaultras.auth.user.User.class)
                                                       Predicate predicate, Pageable pageable) {
        return ResponseEntity.ok(userRepository.findAll(predicate,pageable).map(userDetailsService::createUserDetails));
    }
}
@Data
class ChangePasswordForm {
    private String oldPassword;
    @Size(min=6,max = 72)
    private String newPassword;
}
