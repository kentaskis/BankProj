package com.project.bankproj.controller;

import com.project.bankproj.dto.JwtRequest;
import com.project.bankproj.dto.JwtResponse;
import com.project.bankproj.dto.RefreshJwtRequest;
import com.project.bankproj.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("login")
    public JwtResponse authorisation(@RequestBody JwtRequest jwtRequest) {
        return authService.login(jwtRequest);
    }

    @PostMapping("new-token")
    public JwtResponse getNewAccessToken(@RequestBody RefreshJwtRequest refreshJwtRequest) {
        return  authService.getAccessToken(refreshJwtRequest.getRefreshToken());
    }

    @PostMapping("refresh")
    public JwtResponse refreshJwtTokens(@RequestBody RefreshJwtRequest refreshJwtRequest) {
        return authService.refresh(refreshJwtRequest.getRefreshToken());
    }
}