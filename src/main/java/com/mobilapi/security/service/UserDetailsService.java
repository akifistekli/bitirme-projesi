package com.mobilapi.security.service;


import com.mobilapi.domain.customer.Account;
import com.mobilapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Account account = accountService.findByEmail(email);

        if (accountNotNull(account)) {
            return createUserDetails(account);
        } else {
            throw new UsernameNotFoundException("Account Not found by" + email);
        }
    }

    private UserDetails createUserDetails(Account account) {
        User user = new User(account.getEmail(), account.getPassword(), true, true, true, true, getAuthorities(account));

        return user;
    }

    private List<GrantedAuthority> getAuthorities(Account account) {
        return Arrays.asList(account.getUserRole().getAuthorities()).stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private Boolean accountNotNull(Account account) {
        return account != null;
    }
}
