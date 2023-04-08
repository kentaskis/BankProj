package com.project.bankproj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.bankproj.dto.JwtRequest;
import com.project.bankproj.dto.JwtResponse;
import com.project.bankproj.dto.RefreshJwtRequest;
import com.project.bankproj.exeption.AuthException;
import com.project.bankproj.service.interfaces.AuthService;
import com.project.bankproj.util.DtoCreator;
import com.project.bankproj.validation.JwtFilter;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = {AuthController.class, ResponseExceptionHandler.class})
@DisplayName("Test for AuthController")
@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    @MockBean
    private AuthService authService;
    @MockBean
    private JwtFilter jwtFilter;
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new AuthController(authService))
                .setControllerAdvice(new ResponseExceptionHandler())
                .build();
    }

    @Nested
    @DisplayName("POST /auth/login")
    class Login {
        @Test
        @DisplayName("Test Success for authorisation method")
        void testLoginWithValidCredentials() throws Exception {
            // Define behavior for login with valid credentials
            given(authService.login(any(JwtRequest.class))).willReturn(DtoCreator.getJwtResponse());

            // Test the login endpoint with valid credentials
            mockMvc.perform(post("/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(DtoCreator.getJwtRequest())))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.accessToken").value(DtoCreator.getJwtResponse().getAccessToken()))
                    .andExpect(jsonPath("$.refreshToken").value(DtoCreator.getJwtResponse().getRefreshToken()));

            verify(authService, times(1)).login(any(JwtRequest.class));
        }

        @Test
        @DisplayName("Invalid credentials - 401 Unauthorized")
        void testLoginWithInvalidCredentials() throws Exception {
            // Define behavior for login with invalid credentials
            given(authService.login(any(JwtRequest.class))).willThrow(AuthException.class);

            // Test the login endpoint with invalid credentials
            mockMvc.perform(post("/auth/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(DtoCreator.getJwtRequest())))
                    .andExpect(status().isUnauthorized());

            verify(authService, times(1)).login(any(JwtRequest.class));
        }
    }

    @Nested
    @DisplayName("POST /auth/new-token")
    class NewToken {
        @Test
        @DisplayName("Test for getNewAccessToken method")
        void shouldReturnJwtResponseOnGetNewAccessToken() throws Exception {
            final JwtResponse jwtResponse = DtoCreator.getJwtResponse();
            final RefreshJwtRequest refreshJwtRequest = DtoCreator.geRefreshJwtRequest();
            given(authService.getAccessToken(anyString())).willReturn(jwtResponse);

            mockMvc.perform(post("/auth/new-token")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(refreshJwtRequest)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.accessToken").value(jwtResponse.getAccessToken()))
                    .andExpect(jsonPath("$.refreshToken").value(jwtResponse.getRefreshToken()));
        }

        @Test
        @Description("Tests that an unauthorized status is returned when attempting to get a new access token with an expired refresh token.")
        void givenExpiredToken_whenGetNewAccessToken_thenStatusUnauthorized() throws Exception {
            // Arrange
            String expiredToken = "expiredToken";
            RefreshJwtRequest refreshRequest = new RefreshJwtRequest(expiredToken);

            given(authService.getAccessToken(anyString())).willThrow(ExpiredJwtException.class);

            // Act and Assert
            mockMvc.perform(post("/auth/new-token")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(refreshRequest)))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        @Description("Tests that an unauthorized status is returned when attempting to get a new access token with an invalid refresh token.")
        void givenInvalidToken_whenGetNewAccessToken_thenStatusUnauthorized() throws Exception {
            // Arrange
            String invalidToken = "invalidToken";
            RefreshJwtRequest refreshRequest = new RefreshJwtRequest(invalidToken);

            given(authService.getAccessToken(anyString())).willThrow(MalformedJwtException.class);

            // Act and Assert
            mockMvc.perform(post("/auth/new-token")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(refreshRequest)))
                    .andExpect(status().isUnauthorized());
        }

    }

    @Nested
    @DisplayName("POST /auth/refresh")
    class Refresh {
        @Test
        @DisplayName("Success - 200 OK")
        void shouldReturnJwtResponseOnRefreshJwtTokens() throws Exception {
            final JwtResponse jwtResponse = DtoCreator.getJwtResponse();
            final RefreshJwtRequest refreshJwtRequest = DtoCreator.geRefreshJwtRequest();

            given(authService.refresh(anyString())).willReturn(jwtResponse);

            mockMvc.perform(post("/auth/refresh")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(refreshJwtRequest)))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.accessToken").value(jwtResponse.getAccessToken()))
                    .andExpect(jsonPath("$.refreshToken").value(jwtResponse.getRefreshToken()));
        }

        @Test
        @Description("Tests that an unauthorized status is returned when attempting to refresh JWT tokens with an expired refresh token.")
        void givenExpiredToken_whenRefreshJwtTokens_thenStatusUnauthorized() throws Exception {
            // Arrange
            String expiredToken = "expiredToken";
            RefreshJwtRequest refreshRequest = new RefreshJwtRequest(expiredToken);

            given(authService.refresh(anyString())).willThrow(ExpiredJwtException.class);

            // Act and Assert
            mockMvc.perform(post("/auth/refresh")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(refreshRequest)))
                    .andExpect(status().isUnauthorized());
        }

        @Test
        @Description("Tests that an unauthorized status is returned when attempting to refresh JWT tokens with an invalid refresh token.")
        void givenInvalidToken_whenRefreshJwtTokens_thenStatusUnauthorized() throws Exception {
            // Arrange
            String invalidToken = "invalidToken";
            RefreshJwtRequest refreshRequest = new RefreshJwtRequest(invalidToken);

            given(authService.refresh(anyString())).willThrow(MalformedJwtException.class);

            // Act and Assert
            mockMvc.perform(post("/auth/refresh")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(refreshRequest)))
                    .andExpect(status().isUnauthorized());
        }
    }
}