package com.pragma.user.infrastructure.out.jpa.adapter;

import com.pragma.user.domain.model.Role;
import com.pragma.user.domain.spi.IRolePersistencePort;
import com.pragma.user.infrastructure.exception.RoleNotFoundException;
import com.pragma.user.infrastructure.helper.constants.ExceptionConstants;
import com.pragma.user.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.user.infrastructure.out.jpa.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolePersistencePort {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public Role findRoleByName(String roleName) {
        RoleEntity roleEntity=  roleRepository.findByName(roleName)
                .orElseThrow(() -> new RoleNotFoundException(ExceptionConstants.ROLE_NOT_FOUND));

        return modelMapper.map(roleEntity, Role.class);
    }

    @Override
    public Role saveRole(Role role) {
        RoleEntity roleEntity = roleRepository.save(modelMapper.map(role, RoleEntity.class));
        return modelMapper.map(roleEntity, Role.class);
    }
}
