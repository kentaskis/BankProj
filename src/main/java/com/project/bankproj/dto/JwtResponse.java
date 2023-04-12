package com.project.bankproj.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Getter
@AllArgsConstructor
public class JwtResponse {
    private static final String AUTH_TYPE = "Bearer";
    private String accessToken;
    private String refreshToken;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JwtResponse that = (JwtResponse) o;
        return StringUtils.equals(accessToken, that.accessToken) && StringUtils.equals(refreshToken, that.refreshToken);
    }
    @Override
    public int hashCode() {
        return Objects.hash(accessToken,refreshToken, AUTH_TYPE);
    }
}