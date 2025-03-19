package com.pragma.user.infrastructure.helper.constants;

public class SecurityConstants {

    private SecurityConstants() {}

    public static final String[] PUBLIC_ENDPOINTS = {
            "/v1/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/api/v1/auth/**"
    };

    public static final String[] ADMIN_ENDPOINTS = {
            "/api/v1/users/owner"
    };
}
