package com.hoang.hncstore.dto.user;

import com.hoang.hncstore.constant.RegexConstants;
import com.hoang.hncstore.enums.RoleType;
import com.hoang.hncstore.enums.UserStatus;
import jakarta.validation.constraints.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {

    @NotBlank
    @Pattern(regexp = RegexConstants.USERNAME_REGEX, message = "{USERNAME_REGEX}")
    @Size(min = 6, max = 50, message = "{USERNAME_SIZE}")
    String username;

    @Email(message = "{INVALID_EMAIL}")
    String email;

    @NotBlank(message = "{NOT_BLANK}")
    @Pattern(regexp = RegexConstants.PHONE_NUMBER_REGEX, message = "{PHONE_NUMBER_REGEX}")
    String phoneNumber;

    @Pattern(regexp = RegexConstants.PASSWORD_REGEX, message = "{PASSWORD_REGEX}")
    @Size(min = 8, max = 30, message = "{PASSWORD_SIZE}")
    String password;

    String firstName;

    String lastName;

    @NotNull(message = "{STATUS_NOT_NULL}")
    UserStatus status;

    @NotNull(message = "{ROLES_NOT_NULL}")
    Set<RoleType> roles;
}
