package com.hoang.hncstore.controller;

import com.hoang.hncstore.dto.ApiResponse;
import com.hoang.hncstore.dto.user.CreateUserRequest;
import com.hoang.hncstore.dto.user.UpdateUserRequest;
import com.hoang.hncstore.dto.user.UserResponse;
import com.hoang.hncstore.enums.SuccessCode;
import com.hoang.hncstore.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllUsers() {
        return ResponseEntity.status(SuccessCode.GET_ALL_USERS.getHttpStatus())
                .body(
                        ApiResponse.success(userService.getAllUsers(), SuccessCode.ADD_USER)
                );
    }

    @GetMapping("/{username}")
    public ResponseEntity<ApiResponse<UserResponse>> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.status(SuccessCode.GET_ALL_USERS.getHttpStatus())
                .body(
                        ApiResponse.success(userService.getUserByUsername(username), SuccessCode.ADD_USER)
                );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> updateUser(@RequestBody UpdateUserRequest updateUserRequest, @PathVariable String id) {
        return ResponseEntity.status(SuccessCode.UPDATE_USER.getHttpStatus())
                .body(
                        ApiResponse.success(userService.updateUser(updateUserRequest, id), SuccessCode.UPDATE_USER)
                );
    }
}
