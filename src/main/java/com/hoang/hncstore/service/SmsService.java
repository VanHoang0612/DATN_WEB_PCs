package com.hoang.hncstore.service;

import com.hoang.hncstore.configuration.TwilioConfig;
import com.hoang.hncstore.enums.ErrorCode;
import com.hoang.hncstore.exception.AppException;
import com.hoang.hncstore.utils.DataNormalize;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsService {
    private final TwilioConfig twilioConfig;

    public void sendSms(String to, String body) {
        String phoneNumber = DataNormalize.normalizePhoneNumber(to);
        try {

            Message message = Message.creator(
                            new PhoneNumber(phoneNumber),
                            new PhoneNumber(twilioConfig.getTrialNumber()),
                            body
                    )
                    .create();
        } catch (Exception e) {
            log.error("Error sending SMS", e);
            throw new AppException(ErrorCode.INTERNAL_SERVER);
        }

    }
}
