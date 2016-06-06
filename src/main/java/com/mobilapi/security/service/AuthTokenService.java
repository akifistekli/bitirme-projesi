package com.mobilapi.security.service;

import com.mobilapi.domain.customer.AuthToken;
import com.mobilapi.repository.AuthTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthTokenService {

    @Autowired
    private AuthTokenRepository authTokenRepository;

    public AuthToken create(AuthToken authToken) {
        return authTokenRepository.saveAndFlush(authToken);
    }

    public AuthToken findAccountByTokenAndSeries(String token, String series) {
        return authTokenRepository.findAccountByTokenAndSeries(token, series);
    }

    public void deleteByTokenAndSeries(String token, String series) {
        authTokenRepository.deleteByTokenAndSeries(token, series);
    }

    public AuthToken update(AuthToken authToken) {
        return authTokenRepository.save(authToken);
    }

    public void deleteExpirtedTokens() {
        authTokenRepository.deleteTimedoutTokens();
    }
}
