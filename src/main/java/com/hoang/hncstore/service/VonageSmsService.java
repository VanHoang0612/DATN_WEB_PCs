package com.hoang.hncstore.service;

import com.hoang.hncstore.enums.ErrorCode;
import com.hoang.hncstore.exception.AppException;
import com.hoang.hncstore.utils.DataNormalize;
import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class VonageSmsService {
    private final VonageClient vonageClient;

    public void sendSms(String to, String body) {
        String phoneNumber = DataNormalize.normalizePhoneNumber(to);
        TextMessage message = new TextMessage("HNC Store", phoneNumber, body);

        SmsSubmissionResponse response = vonageClient.getSmsClient()
                .submitMessage(message);

        if (response.getMessages()
                .get(0)
                .getStatus() == MessageStatus.OK) {
            log.info("SMS Sent Successfully");
        } else {
            String errorText = response.getMessages()
                    .get(0)
                    .getErrorText();
            log.error("Message failed with error: {}", errorText);
            throw new AppException(ErrorCode.INTERNAL_SERVER);
        }
    }
}
