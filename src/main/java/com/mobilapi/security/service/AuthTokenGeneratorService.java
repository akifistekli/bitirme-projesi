package com.mobilapi.security.service;


import com.mobilapi.domain.customer.Account;
import com.mobilapi.domain.customer.AuthToken;
import com.mobilapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;

@Service
public class AuthTokenGeneratorService {

    private static final String DELIMITER = ":";

    public static final int DEFAULT_SERIES_LENGTH = 16;
    public static final int DEFAULT_TOKEN_LENGTH = 16;

    private final int seriesLength = DEFAULT_SERIES_LENGTH;
    private final int tokenLength = DEFAULT_TOKEN_LENGTH;

    @Autowired
    private AuthTokenService authTokenService;

    @Autowired
    private AccountService accountService;

    private SecureRandom random;

    @PostConstruct
    public void init() {
        random = new SecureRandom();
    }


    public String generateToken(Authentication authentication) {

        User user = null;

        if (authentication == null) {
            return null;
        }

        if (authentication.getPrincipal() instanceof User) {
           user = (User) authentication.getPrincipal();
        }

        Account account = accountService.findByEmail(user.getUsername());

        final String token = generateToken();
        final String series = generateSeries();

        AuthToken authToken = new AuthToken();
        authToken.setToken(token);
        authToken.setSeries(series);
        authToken.setAccount(account);
        authTokenService.create(authToken);

        String[] tokens = new String[]{token, series};

        return encode(tokens);
    }

    public String[] decode(String token) {

        for (int i = 0; i < token.length() % 4; i++) {
            token = token + "=";
        }

        if (!Base64.isBase64(token.getBytes())) {
            throw new InvalidCookieException("User token was not Base64 encoded; value was '" + token + "'");
        }

        String cookieAsPlainText = new String(Base64.decode(token.getBytes()));

        String[] tokens = StringUtils.delimitedListToStringArray(cookieAsPlainText, DELIMITER);

        if (tokens.length < 2) {
            return null;
        }

        return tokens;
    }

    private String encode(String[] tokens) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tokens.length; i++) {
            sb.append(tokens[i]);

            if (i < tokens.length - 1) {
                sb.append(DELIMITER);
            }
        }

        String value = sb.toString();

        sb = new StringBuilder(new String(Base64.encode(value.getBytes())));

        while (sb.charAt(sb.length() - 1) == '=') {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }

    private String generateSeries() {
        byte[] newSeries = new byte[seriesLength];
        random.nextBytes(newSeries);
        return new String(Base64.encode(newSeries));
    }

    private String generateToken() {
        byte[] newToken = new byte[tokenLength];
        random.nextBytes(newToken);
        return new String(Base64.encode(newToken));
    }


}
