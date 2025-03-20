package com.pragma.user.infrastructure.out.jpa.adapter;

import com.pragma.user.domain.model.Role;
import com.pragma.user.infrastructure.exception.RoleNotFoundException;
import com.pragma.user.infrastructure.helper.constants.ExceptionConstants;
import com.pragma.user.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.user.infrastructure.out.jpa.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleJpaAdapterTest {

    @InjectMocks
    private RoleJpaAdapter roleJpaAdapter;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void findRoleByName_WhenIsSuccessful() {
        String roleName = "testRole";
        RoleEntity roleEntity = new RoleEntity(1L, "testRole", "test description");
        Role role = new Role(1L, "testRole", "test description");

        when(roleRepository.findByName(roleName))
                .thenReturn(Optional.of(roleEntity));
        when(modelMapper.map(roleEntity, Role.class))
                .thenReturn(role);

        Role result = roleJpaAdapter.findRoleByName(roleName);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("testRole", result.getName());
        assertEquals("test description", result.getDescription());
    }

    @Test
    void findRoleByName_WhenIThrowRoleNotFoundException() {
        String roleName = "testRole";

        when(roleRepository.findByName(roleName))
                .thenReturn(Optional.empty());

        RoleNotFoundException result = assertThrows(RoleNotFoundException.class, () -> roleJpaAdapter.findRoleByName(roleName));

        assertEquals(ExceptionConstants.ROLE_NOT_FOUND, result.getMessage());
    }

    @Test
    void saveRole() {
        Role role = new Role(null, "testRole", "test description");
        RoleEntity mappedRoleEntity = new RoleEntity(null, "testRole", "test description");
        RoleEntity savedRoleEntity = new RoleEntity(1L, "testRole", "test description");
        Role savedRole = new Role(1L, "testRole", "test description");

        when(modelMapper.map(role, RoleEntity.class))
                .thenReturn(mappedRoleEntity);
        when(roleRepository.save(mappedRoleEntity))
                .thenReturn(savedRoleEntity);
        when(modelMapper.map(savedRoleEntity, Role.class))
                .thenReturn(savedRole);

        Role result = roleJpaAdapter.saveRole(role);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("testRole", result.getName());
        assertEquals("test description", result.getDescription());
    }
}