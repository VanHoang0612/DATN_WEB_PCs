package com.hoang.hncstore.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionData {
    private String contact;
    private String otp;

    @Builder.Default
    private boolean verified = false;
    private String purpose;
}
