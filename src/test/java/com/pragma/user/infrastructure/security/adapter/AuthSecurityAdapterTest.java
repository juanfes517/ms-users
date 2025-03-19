package com.pragma.user.infrastructure.security.adapter;

import com.pragma.user.infrastructure.security.service.IUserAuthenticationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthSecurityAdapterTest {

    @InjectMocks
    private AuthSecurityAdapter authSecurityAdapter;

    @Mock
    private IUserAuthenticationService userAuthenticationService;

    @Test
    void login_WhenIsSuccessful() {
        String email = "test@test.com";
        String password = "password";

        when(userAuthenticationService.login(email, password))
                .thenReturn(true);

        boolean result = authSecurityAdapter.login(email, password);

        assertTrue(result);
    }

    @Test
    void login_WhenInvalid() {
        String email = "test@test.com";
        String password = "incorrect-password";

        when(userAuthenticationService.login(email, password))
                .thenReturn(false);

        boolean result = authSecurityAdapter.login(email, password);

        assertFalse(result);
    }
}