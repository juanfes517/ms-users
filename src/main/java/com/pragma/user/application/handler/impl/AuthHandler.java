package com.pragma.user.application.handler.impl;

import com.pragma.user.application.dto.request.LoginRequestDto;
import com.pragma.user.application.dto.response.AuthResponseDto;
import com.pragma.user.application.handler.IAuthHandler;
import com.pragma.user.domain.api.IAuthServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthHandler implements IAuthHandler {

    private final IAuthServicePort authServicePort;

    @Override
    public AuthResponseDto login(LoginRequestDto loginRequestDto) {
        String accessToken = authServicePort.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());

        if (accessToken == null) {
            return null;
        }

        return AuthResponseDto.builder()
                .email(loginRequestDto.getEmail())
                .accessToken(accessToken)
                .build();
    }
}
