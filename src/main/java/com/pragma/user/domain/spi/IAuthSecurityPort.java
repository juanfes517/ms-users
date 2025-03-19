package com.pragma.user.domain.spi;

public interface IAuthSecurityPort {

    boolean login(String email, String password);
}
