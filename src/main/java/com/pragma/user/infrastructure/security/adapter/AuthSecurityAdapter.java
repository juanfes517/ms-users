package com.pragma.user.infrastructure.security.adapter;

import com.pragma.user.domain.api.IAuthServicePort;
import com.pragma.user.domain.spi.IAuthSecurityPort;
import com.pragma.user.infrastructure.security.service.IUserAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthSecurityAdapter implements IAuthSecurityPort {

    private final IUserAuthenticationService userAuthenticationService;

    @Override
    public boolean login(String email, String password) {
        return userAuthenticationService.login(email, password);
    }
}
