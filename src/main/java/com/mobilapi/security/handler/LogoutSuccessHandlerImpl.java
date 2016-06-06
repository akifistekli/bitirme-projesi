package com.mobilapi.security.handler;


import com.mobilapi.security.constant.Constant;
import com.mobilapi.security.service.AuthTokenGeneratorService;
import com.mobilapi.security.service.AuthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutSuccessHandlerImpl extends AbstractAuthenticationTargetUrlRequestHandler implements
        org.springframework.security.web.authentication.logout.LogoutSuccessHandler {

    @Autowired
    private AuthTokenService authTokenService;

    @Autowired
    private AuthTokenGeneratorService authTokenGeneratorService;

    public void onLogoutSuccess(HttpServletRequest arg0,
                                HttpServletResponse arg1, Authentication arg2) throws IOException,
            ServletException {
        deleteAuthenticationToken(arg0);
        super.handle(arg0, arg1, arg2);

    }

    private void deleteAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(Constant.HEADER_SECURITY_TOKEN);

        if (null == token || token.trim().length() == 0) {
            return;
        }

        String[] tokens = authTokenGeneratorService.decode(token);
        authTokenService.deleteByTokenAndSeries(tokens[0], tokens[1]);
    }
}

