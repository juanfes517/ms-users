package com.pragma.user.application.handler.impl;

import com.pragma.user.application.dto.request.LoginRequestDto;
import com.pragma.user.application.dto.response.AuthResponseDto;
import com.pragma.user.domain.api.IAuthServicePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthHandlerTest {


    @InjectMocks
    private AuthHandler authHandler;

    @Mock
    private IAuthServicePort authServicePort;

    @Test
    void login_WhenIsSuccessfull() {
        String accessToken = "Access-Token";

        LoginRequestDto loginRequestDto = LoginRequestDto.builder()
                .email("test@test.com")
                .password("password")
                .build();

        AuthResponseDto authResponseDto = AuthResponseDto.builder()
                .email(loginRequestDto.getEmail())
                .accessToken(accessToken)
                .build();

        when(authServicePort.login(loginRequestDto.getEmail(), loginRequestDto.getPassword()))
                .thenReturn(accessToken);

        AuthResponseDto result = authHandler.login(loginRequestDto);

        assertNotNull(result);
        assertEquals(authResponseDto.getEmail(), result.getEmail());
        assertEquals(authResponseDto.getAccessToken(), result.getAccessToken());
    }

    @Test
    void login_WhenIsInvalid() {
        LoginRequestDto loginRequestDto = LoginRequestDto.builder()
                .email("test@test.com")
                .password("incorrect-password")
                .build();

        when(authServicePort.login(loginRequestDto.getEmail(), loginRequestDto.getPassword()))
                .thenReturn(null);

        AuthResponseDto result = authHandler.login(loginRequestDto);

        assertNull(result);
    }
}