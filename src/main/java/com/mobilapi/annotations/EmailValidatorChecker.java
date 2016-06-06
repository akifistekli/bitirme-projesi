package com.mobilapi.annotations;


import com.mobilapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class EmailValidatorChecker implements ConstraintValidator<EmailValidator,String> {

    @Autowired
    private AccountService accountService;

    @Override
    public void initialize(EmailValidator constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return accountService.emailNotRegistered(value);
    }
}
