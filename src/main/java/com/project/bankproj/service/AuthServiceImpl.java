package com.project.bankproj.service;

import com.project.bankproj.auth.JwtAuthentication;
import com.project.bankproj.dto.JwtRequest;
import com.project.bankproj.dto.JwtResponse;
import com.project.bankproj.entity.User;
import com.project.bankproj.exeption.AuthException;
import com.project.bankproj.exeption.ErrorMessage;
import com.project.bankproj.repository.RefreshTokenRepository;
import com.project.bankproj.service.interfaces.AuthService;
import com.project.bankproj.service.interfaces.UserService;
import io.jsonwebtoken.Claims;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProvider jwtProvider;

    @Override
    public JwtResponse login(@NonNull JwtRequest authRequest) {
        final User user = userService.getByLogin(authRequest.getLogin())
                .orElseThrow(() -> new AuthException(ErrorMessage.M_USER_NOT_FOUND));
        if (user.getPassword().equals(authRequest.getPassword())) {
            final String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshTokenRepository.save(user.getLogin(), refreshToken);
            return new JwtResponse(jwtProvider.generateAccessToken(user), refreshToken);
        }
        throw new AuthException(ErrorMessage.M_WRONG_CREDENTIALS);
    }

    @Override
    public JwtResponse getAccessToken(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String saveRefreshToken = refreshTokenRepository.findById(claims.getSubject());
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getByLogin(claims.getSubject())
                        .orElseThrow(() -> new AuthException(ErrorMessage.M_USER_NOT_FOUND));
                return new JwtResponse(jwtProvider.generateAccessToken(user), null);
            }
        }
        return new JwtResponse(null, null);
    }

    @Override
    public JwtResponse refresh(@NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            final String saveRefreshToken = refreshTokenRepository.findById(claims.getSubject());
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getByLogin(claims.getSubject())
                        .orElseThrow(() -> new AuthException(ErrorMessage.M_USER_NOT_FOUND));
                final String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshTokenRepository.save(user.getLogin(), newRefreshToken);
                return new JwtResponse(jwtProvider.generateAccessToken(user), newRefreshToken);
            }
        }
        throw new AuthException(ErrorMessage.M_INVALID_TOKEN);
    }

    @Override
    public JwtAuthentication getAuthInfo() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }
}