package com.hoang.hncstore.enums;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER("Internal server error!", HttpStatus.INTERNAL_SERVER_ERROR),
    VALIDATION_FAILED("Validation failed!", HttpStatus.UNPROCESSABLE_ENTITY),
    ROLE_NOT_FOUND("Role not found!", HttpStatus.NOT_FOUND),
    USERNAME_EXISTS("The username already exists!", HttpStatus.CONFLICT),
    EMAIL_EXISTS("The email already exists!", HttpStatus.CONFLICT),
    PHONE_NUMBER_EXISTS("The email already exists!", HttpStatus.CONFLICT),
    USER_NOT_FOUND("The user does not exist!", HttpStatus.NOT_FOUND),
    ;

    private final String message;

    @JsonIgnore
    private final HttpStatus httpStatus;
}
