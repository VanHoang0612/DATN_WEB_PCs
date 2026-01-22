package com.hoang.hncstore.controller;

import com.hoang.hncstore.dto.ApiResponse;
import com.hoang.hncstore.dto.auth.RegisterCustomerRequest;
import com.hoang.hncstore.dto.auth.SendOtpResponse;
import com.hoang.hncstore.dto.auth.SendSmsOtpRequest;
import com.hoang.hncstore.enums.SuccessCode;
import com.hoang.hncstore.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Void>> register(@Valid @RequestBody RegisterCustomerRequest registerCustomerRequest) {
        authService.register(registerCustomerRequest);
        return ResponseEntity.status(SuccessCode.REGISTER.getHttpStatus())
                .body(
                        ApiResponse.success(null, SuccessCode.REGISTER)
                );
    }

    @PostMapping("/send-otp/sms")
    public ResponseEntity<ApiResponse<SendOtpResponse>> sendOtp(@Valid @RequestBody SendSmsOtpRequest sendSmsOtpRequest) {
        SendOtpResponse res = authService.sendSmsOtp(sendSmsOtpRequest);
        return ResponseEntity.ok(
                ApiResponse.success(res, SuccessCode.VERIFY_PHONE_NUMBER)
        );
    }
}
