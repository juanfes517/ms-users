package com.pragma.user.domain.api;

import com.pragma.user.domain.model.User;

public interface IUserServicePort {

    User saveOwner(User user);

    User saveEmployee(User user);

    boolean userHasRole(Long userId, String roleName);

    boolean userHasEmail(Long userId, String email);
}
