package com.project.bankproj.service.interfaces;

import com.project.bankproj.auth.JwtAuthentication;
import com.project.bankproj.dto.JwtRequest;
import com.project.bankproj.dto.JwtResponse;
import lombok.NonNull;

public interface AuthService {
    JwtResponse login(@NonNull JwtRequest authRequest);

    JwtResponse getAccessToken(@NonNull String refreshToken);

    JwtResponse refresh(@NonNull String refreshToken);

    JwtAuthentication getAuthInfo();
}