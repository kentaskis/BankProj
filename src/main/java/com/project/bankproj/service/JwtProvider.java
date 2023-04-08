package com.project.bankproj.service;

import com.project.bankproj.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {
    private final SecretKey jwtAccessSecret;
    private final SecretKey jwtRefreshSecret;


    public JwtProvider(
            @Value("${jwt.secret.access}") String jwtAccessSecret,
            @Value("${jwt.secret.refresh}") String jwtRefreshSecret
    ) {
        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessSecret));
        this.jwtRefreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtRefreshSecret));
    }

    public String generateAccessToken(@NonNull User user) {
        final Instant accessExpirationInstant = LocalDateTime.now()
                .plusMinutes(10)
                .atZone(ZoneId.systemDefault())
                .toInstant();

        return Jwts.builder()
                .setSubject(user.getLogin())
                .setExpiration(Date.from(accessExpirationInstant))
                .signWith(jwtAccessSecret)
                .claim("roles", user.getRoles())
                .claim("firstName", user.getLogin())
                .compact();
    }

    public String generateRefreshToken(@NonNull User user) {
        final Instant refreshExpirationInstant = LocalDateTime.now()
                .plusDays(30)
                .atZone(ZoneId.systemDefault())
                .toInstant();

        return Jwts.builder()
                .setSubject(user.getLogin())
                .setExpiration(Date.from(refreshExpirationInstant))
                .signWith(jwtRefreshSecret)
                .compact();
    }

    public boolean validateToken(@NonNull String token, @NonNull Key seKey) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(seKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.error("Token expired", expEx);
        } catch (UnsupportedJwtException unsEx) {
            log.error("Unsupported jwt", unsEx);
        } catch (MalformedJwtException mjEx) {
            log.error("Malformed jwt", mjEx);
        } catch (SignatureException sEx) {
            log.error("Invalid signature", sEx);
        } catch (Exception e) {
            log.error("invalid token", e);
        }
        return false;
    }

    public boolean validateAccessToken(@NonNull String accessToken) {
        return validateToken(accessToken, jwtAccessSecret);
    }

    public boolean validateRefreshToken(@NonNull String refreshToken) {
        return validateToken(refreshToken, jwtRefreshSecret);
    }

    public Claims getClaims(@NonNull String token, @NonNull Key seKey) {
        return Jwts.parserBuilder()
                .setSigningKey(seKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Claims getAccessClaims(@NonNull String accessToken) {
        return getClaims(accessToken, jwtAccessSecret);
    }

    public Claims getRefreshClaims(@NonNull String refreshToken) {
        return getClaims(refreshToken, jwtRefreshSecret);
    }
}