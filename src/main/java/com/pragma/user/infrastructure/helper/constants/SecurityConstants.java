package com.pragma.user.infrastructure.helper.constants;

public class SecurityConstants {

    private SecurityConstants() {}

    private static final String[] PUBLIC_ENDPOINTS = {
            "/v1/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/api/v1/auth/**"
    };

    private static final String[] ADMIN_ENDPOINTS = {
            "/api/v1/users/owner"
    };

    public static String[] getPublicEndpoints() {
        return PUBLIC_ENDPOINTS.clone();
    }

    public static String[] getAdminEndpoints() {
        return ADMIN_ENDPOINTS.clone();
    }
}
