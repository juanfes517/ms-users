package com.pragma.user.domain.usecase;

import com.pragma.user.domain.api.IRoleServicePort;
import com.pragma.user.domain.model.Role;
import com.pragma.user.domain.spi.IRolePersistencePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleUseCase implements IRoleServicePort {

    private final IRolePersistencePort rolePersistencePort;

    @Override
    public Role findRoleByName(String roleName) {
        return rolePersistencePort.findRoleByName(roleName);
    }
}
