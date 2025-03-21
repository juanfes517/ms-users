package com.pragma.user.domain.spi;

import com.pragma.user.domain.model.Role;

import java.util.Optional;

public interface IRolePersistencePort {

    Role findRoleByName(String roleName);

    Role saveRole(Role role);
}
