package com.hoang.hncstore.dto.user;

import com.hoang.hncstore.constant.RegexConstants;
import com.hoang.hncstore.enums.RoleType;
import com.hoang.hncstore.enums.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UpdateUserRequest {

    @Pattern(regexp = RegexConstants.USERNAME_REGEX, message = "{USERNAME_REGEX}")
    @Size(min = 6, max = 50, message = "{USERNAME_SIZE}")
    String username;

    @Email(message = "{INVALID_EMAIL}")
    String email;

    @Pattern(regexp = RegexConstants.PHONE_NUMBER_REGEX, message = "{PHONE_NUMBER_REGEX}")
    String phoneNumber;

    String firstName;

    String lastName;

    UserStatus status;

    Set<RoleType> roles;
}
