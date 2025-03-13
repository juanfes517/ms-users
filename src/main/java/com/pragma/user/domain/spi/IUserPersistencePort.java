package com.pragma.user.domain.spi;

import com.pragma.user.domain.model.User;

public interface IUserPersistencePort {

    User save(User user);
}
