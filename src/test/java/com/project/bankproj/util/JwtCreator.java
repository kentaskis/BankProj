package com.project.bankproj.util;

import com.project.bankproj.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class JwtCreator {
    public static Jwt getJwt() {
        return Jwt.withTokenValue("token")
                .header("alg", "none")
                .claim("sub", "test")
                .claim("role", new String[]{"test"})
                .build();
    }

    public static Claims getClaims() {
        User user = EntityCreator.getUser();
        final Instant accessExpirationInstant = LocalDateTime.now()
                .plusMinutes(10)
                .atZone(ZoneId.systemDefault())
                .toInstant();
        Claims claims = new DefaultClaims();
        claims.setSubject(user.getLogin());
        claims.setExpiration(Date.from(accessExpirationInstant));
        claims.put("roles", user.getRoles());
        claims.put("firstName", user.getLogin());
        return claims;
    }
}