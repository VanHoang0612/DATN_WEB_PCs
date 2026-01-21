package com.hoang.hncstore.configuration;

import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TwilioInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioInitializer.class);
    private final TwilioConfig twilioConfig;

    @PostConstruct
    public void initTwilio() {
        Twilio.init(twilioConfig.getAccountSid(), twilioConfig.getAuthToken());
        LOGGER.info("Twilio initialized with account SID: {}", twilioConfig.getAccountSid());
    }
}
