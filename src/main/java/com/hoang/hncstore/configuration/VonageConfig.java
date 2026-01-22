package com.hoang.hncstore.configuration;

import com.vonage.client.VonageClient;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "vonage")
@Getter
@Setter
public class VonageConfig {
    private String apiKey;
    private String apiSecret;

    @PostConstruct
    public void init() {
        System.out.println("Vonage API Key: " + apiKey);
        System.out.println("Vonage API Key: " + apiSecret);

    }

    @Bean
    public VonageClient vonageClient() {
        return VonageClient.builder()
                .apiKey(apiKey)
                .apiSecret(apiSecret)
                .build();
    }
}
