package com.project.bankproj.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RefreshTokenRepository {
    public static final String HASH_KEY = "REFRESH-TOKEN";

    private final RedisTemplate<String, String> redisTemplate;

    public RefreshTokenRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(String username, String refreshToken) {
        redisTemplate.opsForHash().put(HASH_KEY, username, refreshToken);
    }

    public String findById(String username) {
        return (String) redisTemplate.opsForHash().get(HASH_KEY, username);
    }
}