package com.hoang.hncstore.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessCode {
    SUCCESS("Success", HttpStatus.OK),
    ADD_USER("Add user success", HttpStatus.CREATED),
    GET_ALL_USERS("Get all users success", HttpStatus.OK),
    UPDATE_USER("Update user success", HttpStatus.OK),
    ;
    private final String message;

    private final HttpStatus httpStatus;
}
