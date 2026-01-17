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
    ;

    private final String message;

    @JsonIgnore
    private final HttpStatus httpStatus;
}
