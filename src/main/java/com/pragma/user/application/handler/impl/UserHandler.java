package com.pragma.user.application.handler.impl;

import com.pragma.user.application.dto.request.OwnerRequestDto;
import com.pragma.user.application.dto.response.UserResponseDto;
import com.pragma.user.application.handler.IUserHandler;
import com.pragma.user.domain.api.IRoleServicePort;
import com.pragma.user.domain.api.IUserServicePort;
import com.pragma.user.domain.model.Role;
import com.pragma.user.domain.model.RoleEnum;
import com.pragma.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IRoleServicePort roleServicePort;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto saveOwner(OwnerRequestDto ownerRequestDto) {
        ownerRequestDto.setPassword(passwordEncoder.encode(ownerRequestDto.getPassword()));

        Role role = roleServicePort.findRoleByName(RoleEnum.OWNER.toString());

        User userMapped = modelMapper.map(ownerRequestDto, User.class);
        userMapped.setRole(role);
        User userSaved = userServicePort.saveUser(userMapped);

        return modelMapper.map(userSaved, UserResponseDto.class);
    }
}
