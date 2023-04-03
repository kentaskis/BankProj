package com.project.bankproj.controller;

import com.project.bankproj.dto.JwtRequest;
import com.project.bankproj.dto.JwtResponse;
import com.project.bankproj.dto.RefreshJwtRequest;
import com.project.bankproj.service.interfaces.AuthService;
import com.project.bankproj.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final UserService userService;

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

    @GetMapping("/new")
    public void newUser(){
        userService.create();
    }
}