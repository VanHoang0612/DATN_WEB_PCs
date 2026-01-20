package com.hoang.hncstore.dto.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    UUID id;
    String username;
    String email;
    String phoneNumber;
    String avatarUrl;
    String firstName;
    String lastName;
    String password;
    String status;
    Set<String> roles;
}
