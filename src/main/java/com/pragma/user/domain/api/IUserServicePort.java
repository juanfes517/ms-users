package com.pragma.user.domain.api;

import com.pragma.user.domain.model.User;

public interface IUserServicePort {

    User saveUser(User user);

    boolean userHasRole(Long userId, String roleName);
}
