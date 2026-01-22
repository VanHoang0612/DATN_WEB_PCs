package com.hoang.hncstore.enums;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER("An error has occurred, please try again later!", HttpStatus.INTERNAL_SERVER_ERROR),
    VALIDATION_FAILED("Validation failed!", HttpStatus.BAD_REQUEST),
    ROLE_NOT_FOUND("Role not found!", HttpStatus.NOT_FOUND),
    USERNAME_EXISTS("The username already exists!", HttpStatus.CONFLICT),
    EMAIL_EXISTS("The email already exists!", HttpStatus.CONFLICT),
    PHONE_NUMBER_EXISTS("The phone number has already been registered!", HttpStatus.CONFLICT),
    USER_NOT_FOUND("The user does not exist!", HttpStatus.NOT_FOUND),
    OTP_EXPIRED("The OTP has expired!", HttpStatus.UNAUTHORIZED),
    OTP_INVALID("The OTP is incorrect!", HttpStatus.UNAUTHORIZED),
    SESSION_NOT_FOUND("The session has expired", HttpStatus.NOT_FOUND),
    OTP_ALREADY_USED("The OTP has been verified, cannot be used again", HttpStatus.CONFLICT),
    ;

    private final String message;

    @JsonIgnore
    private final HttpStatus httpStatus;
}
