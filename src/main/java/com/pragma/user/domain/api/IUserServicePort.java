package com.pragma.user.domain.api;

import com.pragma.user.domain.model.User;

public interface IUserServicePort {

    User saveOwner(User user);

    User saveEmployee(User user);

    User saveCustomer(User user);

    boolean userHasRole(Long userId, String roleName);

    boolean userHasEmail(Long userId, String email);

    Long findUserIdByEmail(String email);

    String findEmailByUserId(Long userId);

    String findCellPhoneNumberById(Long userId);
}
