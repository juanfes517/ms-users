package com.pragma.user.application.handler.impl;

import com.pragma.user.application.dto.request.OwnerRequestDto;
import com.pragma.user.application.dto.response.UserResponseDto;
import com.pragma.user.domain.api.IRoleServicePort;
import com.pragma.user.domain.api.IUserServicePort;
import com.pragma.user.domain.model.Role;
import com.pragma.user.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserHandlerTest {

    @InjectMocks
    private UserHandler userHandler;

    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private IRoleServicePort roleServicePort;

    @Test
    void saveOwnerWhenIsValid() {
        OwnerRequestDto ownerRequestDto = OwnerRequestDto.builder()
                .name("Paco")
                .lastName("Torres")
                .password("password")
                .build();

        Role role = new Role(1L, "ROLE_OWNER", "testDescription");

        User userMapped = User.builder()
                .name("Paco")
                .lastName("Torres")
                .password("encryptedPassword")
                .role(role)
                .build();

        User userSaved = User.builder()
                .id(1L)
                .name("Paco")
                .lastName("Torres")
                .password("encryptedPassword")
                .role(role)
                .build();

        UserResponseDto userResponseDto = UserResponseDto.builder()
                .id(1L)
                .name("Paco")
                .lastName("Torres")
                .build();

        when(passwordEncoder.encode(ownerRequestDto.getPassword()))
                .thenReturn("encryptedPassword");
        when(roleServicePort.findRoleByName("ROLE_OWNER"))
                .thenReturn(role);
        when(modelMapper.map(ownerRequestDto, User.class))
                .thenReturn(userMapped);
        when(userServicePort.saveUser(userMapped))
                .thenReturn(userSaved);
        when(modelMapper.map(userSaved, UserResponseDto.class))
                .thenReturn(userResponseDto);

        UserResponseDto result = userHandler.saveOwner(ownerRequestDto);

        assertNotNull(userResponseDto);
        assertEquals("Paco", result.getName());
        assertEquals("Torres", result.getLastName());

    }
}