package com.sanctaultras.auth.register;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class RegisterForm {

    @Email
    private String email;

    @Size(min=6,max = 72)
    private String password;

    @AssertTrue
    private boolean agreedToTerms;

}
