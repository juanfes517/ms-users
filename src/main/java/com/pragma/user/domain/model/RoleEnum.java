package com.pragma.user.domain.model;

public enum RoleEnum {

    OWNER("ROLE_OWNER");

    private final String roleName;

    RoleEnum(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }
}
