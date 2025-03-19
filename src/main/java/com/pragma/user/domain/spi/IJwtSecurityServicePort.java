package com.pragma.user.domain.spi;

public interface IJwtSecurityServicePort {

    String createToken();

    boolean validateToken(String token);

    String getClaim(String token, String claim);

    String getSubject(String token);
}
