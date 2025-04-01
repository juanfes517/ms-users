package com.pragma.user.domain.spi;

import com.pragma.user.domain.model.Role;

public interface IRolePersistencePort {

    Role findRoleByName(String roleName);

    Role saveRole(Role role);
}
