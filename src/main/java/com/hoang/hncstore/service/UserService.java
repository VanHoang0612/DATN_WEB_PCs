package com.hoang.hncstore.service;

import com.hoang.hncstore.dto.user.CreateUserRequest;
import com.hoang.hncstore.dto.user.UpdateUserRequest;
import com.hoang.hncstore.dto.user.UserResponse;
import com.hoang.hncstore.entity.User;
import com.hoang.hncstore.enums.ErrorCode;
import com.hoang.hncstore.exception.AppException;
import com.hoang.hncstore.mapper.UserMapper;
import com.hoang.hncstore.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {


    RoleService roleService;
    UserMapper userMapper;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public UserResponse addUser(CreateUserRequest request) {

        Optional<User> existingUser = userRepository.findByUsernameOrEmailOrPhoneNumber(request.getUsername(), request.getEmail(), request.getPhoneNumber());
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            if (user.getUsername()
                    .equals(request.getUsername())) {
                throw new AppException(ErrorCode.USERNAME_EXISTS);
            }
            if (user.getEmail()
                    .equals(request.getEmail())) {
                throw new AppException(ErrorCode.EMAIL_EXISTS);
            }
            if (user.getPhoneNumber()
                    .equals(request.getPhoneNumber())) {
                throw new AppException(ErrorCode.PHONE_NUMBER_EXISTS);
            }
        }
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .status(request.getStatus())
                .roles(roleService.getRolesStrictly(request.getRoles()))
                .build();
        return saveUser(user);
    }

    public UserResponse saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toUserResponse(userRepository.save(user));

    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        System.out.println(users);
        return users.stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    public UserResponse getUserByUsername(String username) {

        return userRepository.findByUsername(username)
                .map(userMapper::toUserResponse)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    public UserResponse updateUser(UpdateUserRequest request, String id) {
        User user = userRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateUserFromRequest(request, user);
        if (request.getRoles() != null && !request.getRoles()
                .isEmpty()) {
            user.setRoles(
                    roleService.getRolesStrictly(request.getRoles())
            );
        }
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public boolean existsByPhoneNumber(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
