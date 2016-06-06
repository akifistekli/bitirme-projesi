package com.mobilapi.security.service;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.scheduling.annotation.Scheduled;
        import org.springframework.stereotype.Service;

@Service
public class CleanupAuthenticationTokenScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CleanupAuthenticationTokenScheduler.class);

    @Autowired
    private AuthTokenService authTokenService;

    @Scheduled(cron = "${auth.cron.session.timeout}")
    public void cleanAuthToken() {
        authTokenService.deleteExpirtedTokens();
        LOGGER.info("Clean expired authentication tokens");
    }


}
