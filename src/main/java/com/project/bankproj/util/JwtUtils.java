package com.project.bankproj.util;

import com.project.bankproj.auth.JwtAuthentication;
import com.project.bankproj.entity.enums.Role;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {
    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtTokenData = new JwtAuthentication();
        jwtTokenData.setRoles(getRoles(claims));
        jwtTokenData.setLogin(claims.getSubject());
        return jwtTokenData;
    }

    private static Set<Role> getRoles(Claims claims) {
        final List<String> roles = claims.get("roles", List.class);
        return roles.stream()
                .map(Role::valueOf)
                .collect(Collectors.toSet());
    }
}