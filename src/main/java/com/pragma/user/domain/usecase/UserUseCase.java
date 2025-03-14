package com.pragma.user.domain.usecase;

import com.pragma.user.domain.api.IUserServicePort;
import com.pragma.user.domain.model.User;
import com.pragma.user.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    @Override
    public User saveUser(User user) {
        user.validateDocumentId();
        user.validateCellPhoneNumber();
        user.validateAge();

        return userPersistencePort.save(user);
    }
}
