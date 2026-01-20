package com.hoang.hncstore.controller;

import com.hoang.hncstore.dto.ApiResponse;
import com.hoang.hncstore.dto.user.CreateUserRequest;
import com.hoang.hncstore.dto.user.UserResponse;
import com.hoang.hncstore.enums.SuccessCode;
import com.hoang.hncstore.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/users")
public class UserController {

    UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse<UserResponse>> addUser(@RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.status(SuccessCode.ADD_USER.getHttpStatus())
                .body(
                        ApiResponse.success(userService.addUser(createUserRequest), SuccessCode.ADD_USER)
                );
    }
}
