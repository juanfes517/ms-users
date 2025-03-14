package com.pragma.user.infrastructure.out.jpa.adapter;

import com.pragma.user.domain.model.Role;
import com.pragma.user.domain.model.User;
import com.pragma.user.infrastructure.out.jpa.entity.RoleEntity;
import com.pragma.user.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.user.infrastructure.out.jpa.repository.UserRepository;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserJpaAdapterTest {

    @InjectMocks
    private UserJpaAdapter userJpaAdapter;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void saveWhenIsValid() {
        Role role = new Role();
        role.setId(1L);
        role.setName("ROLE_OWNER");

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setName("ROLE_OWNER");

        User user = User.builder()
                .name("John")
                .lastName("Smith")
                .documentId("12345678")
                .email("john@smith.com")
                .password("encryptedPassword")
                .role(role)
                .build();

        UserEntity mappedUserEntity = UserEntity.builder()
                .name("John")
                .lastName("Smith")
                .documentId("12345678")
                .email("john@smith.com")
                .password("encryptedPassword")
                .role(roleEntity)
                .build();

        UserEntity savedUserEntity = UserEntity.builder()
                .id(1L)
                .name("John")
                .lastName("Smith")
                .documentId("12345678")
                .email("john@smith.com")
                .password("encryptedPassword")
                .role(roleEntity)
                .build();

        User userResult = User.builder()
                .id(1L)
                .name("John")
                .lastName("Smith")
                .documentId("12345678")
                .email("john@smith.com")
                .password("encryptedPassword")
                .role(role)
                .build();

        when(modelMapper.map(user, UserEntity.class))
                .thenReturn(mappedUserEntity);
        when(userRepository.save(mappedUserEntity))
                .thenReturn(savedUserEntity);
        when(modelMapper.map(savedUserEntity, User.class))
                .thenReturn(userResult);

        User result  = userJpaAdapter.save(user);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John", result.getName());
        assertEquals("Smith", result.getLastName());
        assertEquals("12345678", result.getDocumentId());
        assertEquals("john@smith.com", result.getEmail());
        assertEquals("encryptedPassword", result.getPassword());
        assertEquals(role.getName(), result.getRole().getName());
    }
}