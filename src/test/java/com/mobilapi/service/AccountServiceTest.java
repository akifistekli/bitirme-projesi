package com.mobilapi.service;

import com.mobilapi.bulider.AccountBuilder;
import com.mobilapi.domain.customer.Account;
import com.mobilapi.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ShaPasswordEncoder passwordEncoder;


    @Test
    public void shouldSaveNewAccount() {

        Account account = new AccountBuilder().firstName("rohat").email("rohat@test.com").password("12345").bulid();
        when(passwordEncoder.encodePassword("12345", null)).thenReturn("test_password");
        accountService.saveAccount(account);

        assertThat(account.getPassword(), equalTo("test_password"));
    }

    @Test
    public void shouldFindAccountByEmail() {

        Account account = new AccountBuilder().firstName("rohat").email("rohat@test.com").bulid();
        when(accountRepository.findByEmail("rohat@test.com")).thenReturn(account);
        Account account1 = accountService.findByEmail("rohat@test.com");

        assertThat(account, equalTo(account1));
    }

    @Test
    public void shouldReturnTrueWhenEmailNotRegister() {

        when(accountRepository.findByEmail("rohat@test.com")).thenReturn(null);

        assertEquals(accountService.emailNotRegistered("rohat@test.com"), Boolean.TRUE);
    }


}