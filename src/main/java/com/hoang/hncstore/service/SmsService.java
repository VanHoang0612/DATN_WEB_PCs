package com.hoang.hncstore.service;

import com.hoang.hncstore.configuration.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmsService {
    private final TwilioConfig twilioConfig;

    public void sendSms(String to, String body) {
        Message.creator(
                        new PhoneNumber(to),
                        new PhoneNumber(twilioConfig.getTrialNumber()),
                        body
                )
                .create();
    }
}
