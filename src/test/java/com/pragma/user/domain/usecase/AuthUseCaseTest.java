package com.pragma.user.domain.usecase;

import com.pragma.user.domain.spi.IAuthSecurityPort;
import com.pragma.user.domain.spi.IJwtSecurityServicePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthUseCaseTest {

    @InjectMocks
    private AuthUseCase authUseCase;

    @Mock
    private IJwtSecurityServicePort jwtSecurityServicePort;

    @Mock
    private IAuthSecurityPort authSecurityPort;

    @Test
    void login_WhenIsSuccessful() {

        String email = "correct@email.com";
        String password = "password";
        String testToken = "testToken";

        when(authSecurityPort.login(email, password)).thenReturn(true);
        when(jwtSecurityServicePort.createToken()).thenReturn(testToken);

        String result = authUseCase.login(email, password);

        assertNotNull(result);
        assertEquals(testToken, result);
    }

    @Test
    void login_WhenTheEmailOrPasswordIsInvalid() {

        String email = "incorrect@email.com";
        String password = "password";

        when(authSecurityPort.login(email, password)).thenReturn(false);

        String result = authUseCase.login(email, password);

        assertNull(result);
    }
}