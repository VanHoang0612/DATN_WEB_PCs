package com.hoang.hncstore.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twilio")
@Setter
@Getter
public class TwilioConfig {
    private String accountSid;
    private String authToken;
    private String trialNumber;

}
