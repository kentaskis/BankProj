package com.project.bankproj.util;

import org.springframework.security.oauth2.jwt.Jwt;

public class JwtCreator {
    public static Jwt getJwt() {
        return Jwt.withTokenValue("token")
                .header("alg", "none")
                .claim("sub", "test")
                .claim("role", new String[]{"test"})
                .build();
    }
}
