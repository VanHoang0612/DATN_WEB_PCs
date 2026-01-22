package com.hoang.hncstore.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoang.hncstore.constant.PrefixConstants;
import com.hoang.hncstore.dto.OtpSession;
import com.hoang.hncstore.dto.SessionData;
import com.hoang.hncstore.enums.ErrorCode;
import com.hoang.hncstore.enums.OtpPurpose;
import com.hoang.hncstore.exception.AppException;
import com.hoang.hncstore.utils.OtpUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SessionOtpService {


    private final RedisService redisService;
    private final ObjectMapper objectMapper;
    private static final Duration DEFAULT_TTL_IN_MINUTES = Duration.ofMinutes(5);

    public OtpSession createSession(String contact, OtpPurpose purpose) {
        return createSession(contact, purpose, DEFAULT_TTL_IN_MINUTES);
    }

    public OtpSession createSession(String contact, OtpPurpose purpose, Duration ttl) {
        String sessionId = UUID.randomUUID()
                .toString();
        String redisKey = buildRedisKey(sessionId);

        SessionData sessionData = SessionData.builder()
                .contact(contact)
                .otp(OtpUtils.generateOtp())
                .purpose(purpose.name())
                .build();
        try {
            String jsonValue = objectMapper.writeValueAsString(sessionData);
            redisService.saveValue(redisKey, jsonValue, ttl);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing session data", e);
        }
        return new OtpSession(sessionId, sessionData.getOtp(), DEFAULT_TTL_IN_MINUTES.toString());

    }

    public SessionData getSessionData(String sessionId) {
        String redisKey = buildRedisKey(sessionId);
        String jsonValue = redisService.getValue(redisKey);
        if (jsonValue == null) {
            return null;
        }
        try {
            return objectMapper.readValue(jsonValue, SessionData.class);
        } catch (JsonProcessingException e) {
            log.error("Error serializing session data", e);
            throw new AppException(ErrorCode.INTERNAL_SERVER);
        }
    }

    public boolean verifyOtp(String sessionId, String otp) {
        SessionData sessionData = getSessionData(sessionId);
        if (sessionData == null) {
            log.warn("OTP verification failed: sessionId={} not found", sessionId);
            throw new AppException(ErrorCode.OTP_EXPIRED);
        }
        if (sessionData.getOtp() == null) {
            log.warn("OTP verification failed: OTP is null");
            throw new AppException(ErrorCode.OTP_ALREADY_USED);
        }
        if (sessionData.getOtp()
                .equals(otp)) {
            sessionData.setOtp(null);
            sessionData.setVerified(true);
            try {
                String jsonValue = objectMapper.writeValueAsString(sessionData);
                redisService.saveValue(buildRedisKey(sessionId), jsonValue, DEFAULT_TTL_IN_MINUTES);
                log.info("OTP verified successfully: sessionId={} verified", sessionId);
                return true;
            } catch (JsonProcessingException e) {
                log.error("Error serializing session data", e);
                throw new AppException(ErrorCode.INTERNAL_SERVER);
            }

        } else {
            log.warn("OTP verification failed: sessionId={} providedOtp={}", sessionId, otp);
            throw new AppException(ErrorCode.OTP_INVALID);
        }
    }

    private String buildRedisKey(String key) {
        return PrefixConstants.OTP_PREFIX + PrefixConstants.SESSION_PREFIX + key;
    }
}
