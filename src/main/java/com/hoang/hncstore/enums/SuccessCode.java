package com.hoang.hncstore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    SUCCESS("Success", HttpStatus.OK),
    ADD_USER("Add user success", HttpStatus.CREATED),
    ;
    private final String message;

    private final HttpStatus httpStatus;
}
