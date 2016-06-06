package com.mobilapi.security;


import com.mobilapi.domain.customer.Account;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class CurrentAccountHolder {

    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public boolean hasNotCurrentUser() {
        return account == null;
    }
}
