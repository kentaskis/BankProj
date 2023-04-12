package com.project.bankproj.service;

import com.project.bankproj.dto.JwtRequest;
import com.project.bankproj.dto.JwtResponse;
import com.project.bankproj.dto.RefreshJwtRequest;
import com.project.bankproj.entity.User;
import com.project.bankproj.exeption.AuthException;
import com.project.bankproj.repository.RefreshTokenRepository;
import com.project.bankproj.service.interfaces.UserService;
import com.project.bankproj.util.DtoCreator;
import com.project.bankproj.util.EntityCreator;
import com.project.bankproj.util.JwtCreator;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserService userService;
    @Mock
    private RefreshTokenRepository refreshTokenRepository;
    @Mock
    private JwtProvider jwtProvider;

    private AuthServiceImpl authService;

    @BeforeEach
    public void setup() {
        authService = new AuthServiceImpl(userService, refreshTokenRepository, jwtProvider);

    }

    @Test
    void login() {
        JwtRequest jwtRequest = DtoCreator.getJwtRequest();
        User user = EntityCreator.getUser();
        JwtResponse expectedJwtResponse = DtoCreator.getJwtResponse();

        when(userService.getByLogin(jwtRequest.getLogin())).thenReturn(Optional.of(user));
        when(jwtProvider.generateRefreshToken(user)).thenReturn(expectedJwtResponse.getRefreshToken());

        JwtResponse actualJwtResponse = authService.login(jwtRequest);

        assertEquals(expectedJwtResponse.getRefreshToken(), actualJwtResponse.getRefreshToken());
        verify(refreshTokenRepository, times(1)).save(jwtRequest.getLogin(), expectedJwtResponse.getRefreshToken());
    }

    @Test
    void login_withInvalidCredentials_throwsAuthException() {
        JwtRequest jwtRequest = DtoCreator.getJwtRequest();

        when(userService.getByLogin(jwtRequest.getLogin())).thenReturn(Optional.empty());

        assertThrows(AuthException.class, () -> authService.login(jwtRequest));
        verifyNoInteractions(refreshTokenRepository);
    }

    @Test
    void getAccessToken() {
        RefreshJwtRequest refreshJwtRequest = DtoCreator.geRefreshJwtRequest();
        User user = EntityCreator.getUser();
        String token = DtoCreator.getJwtResponse().getAccessToken();
        JwtResponse expectedJwtResponse = new JwtResponse(token, null);

        when(jwtProvider.validateRefreshToken(refreshJwtRequest.getRefreshToken())).thenReturn(true);
        when(jwtProvider.getRefreshClaims(refreshJwtRequest.getRefreshToken())).thenReturn(JwtCreator.getClaims());
        when(refreshTokenRepository.findById(user.getLogin())).thenReturn(refreshJwtRequest.getRefreshToken());
        when(userService.getByLogin(user.getLogin())).thenReturn(Optional.of(user));
        when(jwtProvider.generateAccessToken(user)).thenReturn(token);

        JwtResponse actualJwtResponse = authService.getAccessToken(refreshJwtRequest.getRefreshToken());

        assertEquals(expectedJwtResponse, actualJwtResponse);
    }


    @Test
    void getAccessToken_withInvalidRefreshToken_returnsJwtResponseWithNullValues() {
        String refreshToken = "invalid_refresh_token";

        when(jwtProvider.validateRefreshToken(refreshToken)).thenReturn(false);

        JwtResponse jwtResponse = authService.getAccessToken(refreshToken);

        assertNull(jwtResponse.getAccessToken());
        assertNull(jwtResponse.getRefreshToken());
        verifyNoInteractions(userService, refreshTokenRepository);
    }

    @Test
    void testRefresh() {
        RefreshJwtRequest refreshJwtRequest = DtoCreator.geRefreshJwtRequest();
        User user = EntityCreator.getUser();
        String token = DtoCreator.getJwtResponse().getAccessToken();
        JwtResponse expectedJwtResponse = new JwtResponse(token, null);
        final Claims claims = JwtCreator.getClaims();

        when(jwtProvider.validateRefreshToken(refreshJwtRequest.getRefreshToken())).thenReturn(true);
        when(jwtProvider.getRefreshClaims(refreshJwtRequest.getRefreshToken())).thenReturn(claims);
        when(refreshTokenRepository.findById(claims.getSubject())).thenReturn(refreshJwtRequest.getRefreshToken());
        when(jwtProvider.generateRefreshToken(user)).thenReturn(expectedJwtResponse.getRefreshToken());
        when(jwtProvider.generateAccessToken(user)).thenReturn(expectedJwtResponse.getAccessToken());
        when(userService.getByLogin(user.getLogin())).thenReturn(Optional.of(user));

        final JwtResponse actualJwtResponse = authService.refresh(refreshJwtRequest.getRefreshToken());

        assertNotNull(actualJwtResponse);
        assertEquals(actualJwtResponse, expectedJwtResponse);
        verify(refreshTokenRepository).save(user.getLogin(), expectedJwtResponse.getRefreshToken());
    }

    @Test
    void testRefreshWithInvalidToken() {
        final String refreshToken = "testRefreshToken";

        when(jwtProvider.validateRefreshToken(refreshToken)).thenReturn(false);

        assertThrows(AuthException.class, () -> authService.refresh(refreshToken));
        verify(refreshTokenRepository, never()).findById(ArgumentMatchers.any());
        verify(refreshTokenRepository, never()).save(ArgumentMatchers.any(), ArgumentMatchers.any());
        verify(userService, never()).getByLogin(ArgumentMatchers.any());
    }
}