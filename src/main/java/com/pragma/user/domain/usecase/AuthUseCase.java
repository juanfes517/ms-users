package com.pragma.user.domain.usecase;

import com.pragma.user.domain.api.IAuthServicePort;
import com.pragma.user.domain.spi.IAuthSecurityPort;
import com.pragma.user.domain.spi.IJwtSecurityServicePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthUseCase implements IAuthServicePort {

    private final IJwtSecurityServicePort jwtSecurityServicePort;
    private final IAuthSecurityPort authSecurityPort;

    @Override
    public String login(String email, String password) {
        boolean loginStatus = authSecurityPort.login(email, password);

        if (!loginStatus) {
            return null;
        }

        return jwtSecurityServicePort.createToken();
    }
}
