package com.mobilapi.security.filter;

import com.mobilapi.domain.customer.Account;
import com.mobilapi.domain.customer.AuthToken;
import com.mobilapi.security.constant.Constant;
import com.mobilapi.security.handler.TokenBasedAuthenticationSuccessHandler;
import com.mobilapi.security.service.AuthTokenGeneratorService;
import com.mobilapi.security.service.AuthTokenService;
import com.mobilapi.security.service.NoOpAuthenticationManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class TokenBasedAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenBasedAuthenticationSuccessHandler.class);

    private final String TOKEN_FILTER_APPLIED = "TOKEN_FILTER_APPLIED";

    @Autowired
    private AuthTokenGeneratorService authTokenGeneratorService;

    @Autowired
    private AuthTokenService authTokenService;

    public TokenBasedAuthenticationFilter(String defaultFilterProcessesUrl) {

        super(defaultFilterProcessesUrl);
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        super.setAuthenticationManager(new NoOpAuthenticationManager());
        setAuthenticationSuccessHandler(new TokenBasedAuthenticationSuccessHandler());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        AbstractAuthenticationToken userAuthenticationToken = null;

        request.setAttribute(TOKEN_FILTER_APPLIED, Boolean.TRUE);

        String token = request.getHeader(Constant.HEADER_SECURITY_TOKEN);
        userAuthenticationToken = authenticateByToken(token);
        if (userAuthenticationToken == null)
            throw new AuthenticationServiceException("Bad Token");

        return userAuthenticationToken;
    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1,
                         FilterChain arg2) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;

        if (request.getAttribute(TOKEN_FILTER_APPLIED) != null) {
            arg2.doFilter(request, response);
        } else {
            super.doFilter(arg0, arg1, arg2);
        }

    }

    private AbstractAuthenticationToken authenticateByToken(String token) {

        if (null == token) {
            return null;
        }

        AbstractAuthenticationToken authToken = null;

        try {
            String[] tokens = authTokenGeneratorService.decode(token);

            AuthToken tokenEntry = authTokenService.findAccountByTokenAndSeries(tokens[0], tokens[1]);

            if (null == tokenEntry) {
                return null;
            }

            User securityUser = (User) createUserDetails(tokenEntry.getAccount());

            authToken = new UsernamePasswordAuthenticationToken(securityUser.getUsername(), "", securityUser.getAuthorities());

        } catch (Exception ex) {
            LOGGER.error("Failed to authenticate user for token" + token + "{ }", ex);
        }

        return authToken;
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
}

