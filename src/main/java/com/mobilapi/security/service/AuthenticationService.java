package com.mobilapi.security.service;

import com.mobilapi.domain.customer.Account;
import com.mobilapi.security.CurrentAccountHolder;
import com.mobilapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ApplicationContext applicationContext;

    public void authenticate(String email, String password) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public Account getCurrentAccount() {
        return isCurrentAccountAuthenticated() ? getOrFindCurrentAccount() : null;
    }

    private Account getOrFindCurrentAccount() {
        CurrentAccountHolder currentAccountHolder = getCurrentAccountHolder();
        if (currentAccountHolder.hasNotCurrentUser()) {
            Account currentAccount = accountService.findByEmail(getAuthentication().getName());
            currentAccountHolder.setAccount(currentAccount);
        }

        return currentAccountHolder.getAccount();
    }

    public Boolean isCurrentAccountAuthenticated() {
        return !hasRole("ROLE_ANONYMOUS");
    }

    public Boolean isCurrentAccountAnonymous() {
        return isCurrentAccountAuthenticated();
    }

    private Boolean hasRole(String role) {
        return getAuthentication().getAuthorities().stream()
                .filter(a -> a.getAuthority().equals(role))
                .findFirst()
                .isPresent();
    }

    private CurrentAccountHolder getCurrentAccountHolder() {
        return applicationContext.getBean(CurrentAccountHolder.class);
    }

    private Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
