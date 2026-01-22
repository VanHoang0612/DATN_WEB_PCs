package com.hoang.hncstore.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OtpSession {
    String sessionId;
    String otp;
    String ttlInMinutes;
}
