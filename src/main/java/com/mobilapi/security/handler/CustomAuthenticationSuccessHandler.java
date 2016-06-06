package com.mobilapi.security.handler;


import com.mobilapi.security.constant.Constant;
import com.mobilapi.security.service.AuthTokenGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    @Autowired
    private AuthTokenGeneratorService authTokenGeneratorService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

        final String authToken = authTokenGeneratorService.generateToken(authentication);

        response.addHeader(Constant.HEADER_SECURITY_TOKEN, authToken);


    }
}
