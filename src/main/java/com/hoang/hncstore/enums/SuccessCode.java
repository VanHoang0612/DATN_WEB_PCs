package com.hoang.hncstore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    SUCCESS("Success", HttpStatus.OK),
    ;
    private final String message;

    private final HttpStatus httpStatus;
}
