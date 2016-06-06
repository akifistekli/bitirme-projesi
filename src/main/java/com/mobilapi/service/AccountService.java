package com.mobilapi.service;


import com.mobilapi.domain.customer.Account;
import com.mobilapi.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ShaPasswordEncoder shaPasswordEncoder;


    public Account findByEmail(String email) {
        Account account = accountRepository.findByEmail(email);

        return account;
    }

    public Account saveAccount(Account account) {
        String hashed_password = shaPasswordEncoder.encodePassword(account.getPassword(), null);
        account.setPassword(hashed_password);
        return accountRepository.save(account);
    }

    public boolean emailNotRegistered(String email) {
        return accountRepository.findByEmail(email) == null;
    }


}
