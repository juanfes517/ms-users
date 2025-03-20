package com.pragma.user.domain.model;

public enum RoleEnum {

    ADMIN("ADMIN"),
    OWNER("OWNER");

    private final String roleName;

    RoleEnum(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }
}
