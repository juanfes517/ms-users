package com.pragma.user.domain.usecase;

import com.pragma.user.domain.model.Role;
import com.pragma.user.domain.spi.IRolePersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleUseCaseTest {

    @InjectMocks
    private RoleUseCase roleUseCase;

    @Mock
    private IRolePersistencePort rolePersistencePort;

    @Test
    void findRoleByNameWhenReturnExistingRole() {
        String roleName = "testRole";
        Role existingRole = new Role(1L, "testRole", "Role description");

        when(rolePersistencePort.findRoleByName(roleName))
                .thenReturn(existingRole);

        Role result = roleUseCase.findRoleByName(roleName);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("testRole", result.getName());
        assertEquals("Role description", result.getDescription());
    }
}