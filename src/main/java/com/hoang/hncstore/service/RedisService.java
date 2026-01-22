package com.hoang.hncstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final StringRedisTemplate redisTemplate;

    public void saveValue(String key, String value, Duration ttl) {
        redisTemplate.opsForValue()
                .set(key, value, ttl);
    }

    public String getValue(String key) {
        return redisTemplate.opsForValue()
                .get(key);
    }

    public void deleteValue(String key) {
        redisTemplate.delete(key);
    }
}
