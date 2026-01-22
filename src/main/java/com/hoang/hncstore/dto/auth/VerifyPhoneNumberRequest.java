package com.hoang.hncstore.dto.auth;

import com.hoang.hncstore.constant.RegexConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VerifyPhoneNumberRequest {
    @NotBlank(message = "{NOT_BLANK}")
    @Pattern(regexp = RegexConstants.PHONE_NUMBER_REGEX, message = "{PHONE_NUMBER_REGEX}")
    String phoneNumber;

    @NotBlank(message = "{NOT_BLANK}")
    @Pattern(regexp = "^\\d{6}$", message = "{VERIFICATION_CODE_REGEX}")
    String verificationCode;
}
