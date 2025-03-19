package com.pragma.user.infrastructure.security.service;

public interface IUserAuthenticationService {

    boolean login(String email, String password);
}
