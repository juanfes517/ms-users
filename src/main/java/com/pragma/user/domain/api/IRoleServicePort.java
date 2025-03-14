package com.pragma.user.domain.api;

import com.pragma.user.domain.model.Role;

public interface IRoleServicePort {

    Role findRoleByName(String roleName);
}
