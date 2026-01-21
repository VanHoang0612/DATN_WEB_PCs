package com.hoang.hncstore.service;

import com.hoang.hncstore.dto.auth.RegisterCustomerRequest;
import com.hoang.hncstore.entity.User;
import com.hoang.hncstore.enums.ErrorCode;
import com.hoang.hncstore.enums.RoleType;
import com.hoang.hncstore.enums.UserStatus;
import com.hoang.hncstore.exception.AppException;
import com.hoang.hncstore.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final RoleService roleService;

    public void register(RegisterCustomerRequest request) {
        if (userService.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new AppException(ErrorCode.PHONE_NUMBER_EXISTS);
        }

        String username;
        do {
            username = "user_" + UUID.randomUUID()
                    .toString()
                    .substring(0, 6);
        } while (userService.existsByUsername(username));

        User user = User.builder()
                .phoneNumber(request.getPhoneNumber())
                .username(username)
                .password(PasswordUtils.generatePassword(8))
                .status(UserStatus.INACTIVE)
                .roles(
                        Set.of(roleService.findByRoleName(RoleType.USER))
                )
                .build();
        
        userService.saveUser(user);
    }
}
