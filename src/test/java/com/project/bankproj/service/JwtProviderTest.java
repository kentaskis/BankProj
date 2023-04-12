package com.project.bankproj.service;

import com.project.bankproj.entity.User;
import com.project.bankproj.entity.enums.Role;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class JwtProviderTest {

    private JwtProvider jwtProvider;
    private final Set<Role> roles = new HashSet<>();

    @BeforeEach
    void setup() {
        jwtProvider = new JwtProvider(
                "BuTyO1qByhgCP/CbIjModrxRHbg//w+KxVtGC83RNmw3kE0G6ozSR5JLglrIDoswD8PIzU72oX9A4uyJc+V5jA==",
                "qAlz8yAEnxvkQBIu3IbbP9S7Bndl3Urlan0ydPrb1rBUnmySi80+KCoYYsfd/THUFZLWH8yBlJ/kQrbkmB3+4w=="
        );
        roles.add(Role.USER);
    }

    @Test
    void generateAccessToken() {
        User user = new User();
        user.setLogin("testuser");
        user.setRoles(roles);
        String token = jwtProvider.generateAccessToken(user);
        assertNotNull(token);
    }

    @Test
    void generateRefreshToken() {
        User user = new User();
        user.setLogin("testuser");
        user.setRoles(roles);
        String token = jwtProvider.generateRefreshToken(user);
        assertNotNull(token);
    }

    @Test
    void validateToken() {
        User user = new User();
        user.setLogin("testuser");
        user.setRoles(roles);
        String token = jwtProvider.generateAccessToken(user);
        assertTrue(jwtProvider.validateToken(token, jwtProvider.getJwtAccessSecret()));
    }

    @Test
    void validateTokenWithExpiredToken() {
        User user = new User();
        user.setLogin("testuser");
        user.setRoles(roles);

        // Generate token with short expiration time
        LocalDateTime expirationTime = LocalDateTime.now().minusHours(5);
        final Date accessExpirationDate = Date.from(expirationTime.atZone(ZoneId.systemDefault()).toInstant());
        String token = io.jsonwebtoken.Jwts.builder()
                .setSubject(user.getLogin())
                .setExpiration(accessExpirationDate)
                .signWith(jwtProvider.getJwtAccessSecret())
                .claim("roles", user.getRoles())
                .claim("firstName", user.getLogin())
                .compact();

        // Validate expired token
        assertFalse(jwtProvider.validateToken(token, jwtProvider.getJwtAccessSecret()));
    }

    @Test
    void validateAccessToken() {
        User user = new User();
        user.setLogin("testuser");
        user.setRoles(roles);
        String token = jwtProvider.generateAccessToken(user);
        assertTrue(jwtProvider.validateAccessToken(token));
    }

    @Test
    void validateRefreshToken() {
        User user = new User();
        user.setLogin("testuser");
        user.setRoles(roles);
        String token = jwtProvider.generateRefreshToken(user);
        assertTrue(jwtProvider.validateRefreshToken(token));
    }

    @Test
    void getAccessClaims() {
        User user = new User();
        user.setLogin("testuser");
        user.setRoles(roles);
        String token = jwtProvider.generateAccessToken(user);
        Claims claims = jwtProvider.getAccessClaims(token);
        assertNotNull(claims);
        assertEquals(user.getLogin(), claims.getSubject());
        assertEquals(user.getLogin(), claims.get("firstName", String.class));
    }

    @Test
    void testValidateTokenValidToken() {
        User user = new User();
        user.setLogin("testuser");
        user.setRoles(roles);

        String token = jwtProvider.generateAccessToken(user);
        boolean result = jwtProvider.validateToken(token, jwtProvider.getJwtAccessSecret());

        assertTrue(result);
    }

    @Test
    void testValidateTokenInvalidToken() {
        boolean result = jwtProvider.validateToken("invalidtoken", jwtProvider.getJwtAccessSecret());

        assertFalse(result);
    }

    @Test
    void testValidateAccessTokenValidToken() {
        User user = new User();
        user.setLogin("testuser");
        user.setRoles(roles);

        String token = jwtProvider.generateAccessToken(user);
        boolean result = jwtProvider.validateAccessToken(token);

        assertTrue(result);
    }

    @Test
    void testValidateAccessTokenInvalidToken() {
        boolean result = jwtProvider.validateAccessToken("invalidtoken");

        assertFalse(result);
    }

    @Test
    void testValidateRefreshTokenValidToken() {
        User user = new User();
        user.setLogin("testuser");
        user.setRoles(roles);

        String token = jwtProvider.generateRefreshToken(user);
        boolean result = jwtProvider.validateRefreshToken(token);

        assertTrue(result);
    }

    @Test
    void testValidateRefreshTokenInvalidToken() {
        boolean result = jwtProvider.validateRefreshToken("invalidtoken");

        assertFalse(result);
    }

    @Test
    void testGetClaims() {
        User user = new User();
        user.setLogin("testuser");
        user.setRoles(roles);

        String token = jwtProvider.generateAccessToken(user);
        Claims claims = jwtProvider.getClaims(token, jwtProvider.getJwtAccessSecret());

        assertEquals(user.getLogin(), claims.getSubject());
        assertEquals(user.getLogin(), claims.get("firstName"));
    }

    @Test
    void testGetAccessClaims() {
        User user = new User();
        user.setLogin("testuser");
        user.setRoles(roles);

        String token = jwtProvider.generateAccessToken(user);
        Claims claims = jwtProvider.getAccessClaims(token);

        assertEquals(user.getLogin(), claims.getSubject());
        assertEquals(user.getLogin(), claims.get("firstName"));
    }

    @Test
    void testGetRefreshClaims() {
        User user = new User();
        user.setLogin("testuser");
        user.setRoles(roles);

        String token = jwtProvider.generateRefreshToken(user);
        Claims claims = jwtProvider.getRefreshClaims(token);

        assertEquals(user.getLogin(), claims.getSubject());
    }
}