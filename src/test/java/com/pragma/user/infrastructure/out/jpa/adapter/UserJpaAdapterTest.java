package com.pragma.user.infrastructure.out.jpa.adapter;

import com.pragma.user.domain.model.Role;
import com.pragma.user.domain.model.User;
import com.pragma.user.infrastructure.exception.UserNotFoundException;
import com.pragma.user.infrastructure.helper.constants.ExceptionConstants;
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

import java.util.Optional;

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
    void save_WhenIsSuccessful() {
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

    @Test
    void findById_WhenIsSuccessful() {
        Long id = 1L;

        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .name("John")
                .lastName("Smith")
                .documentId("12345678")
                .email("john@smith.com")
                .password("encryptedPassword")
                .role(new RoleEntity(1L, "TEST", "TEST"))
                .build();

        User mappedUser = User.builder()
                .id(1L)
                .name("John")
                .lastName("Smith")
                .documentId("12345678")
                .email("john@smith.com")
                .password("encryptedPassword")
                .role(new Role(1L, "TEST", "TEST"))
                .build();

        when(userRepository.findById(id))
                .thenReturn(Optional.of(userEntity));
        when(modelMapper.map(userEntity, User.class))
                .thenReturn(mappedUser);

        User result = userJpaAdapter.findById(id);

        assertNotNull(result);
        assertEquals(mappedUser.getId(), result.getId());
        assertEquals(mappedUser.getName(), result.getName());
        assertEquals(mappedUser.getLastName(), result.getLastName());
        assertEquals(mappedUser.getDocumentId(), result.getDocumentId());
        assertEquals(mappedUser.getEmail(), result.getEmail());
        assertEquals(mappedUser.getPassword(), result.getPassword());
    }

    @Test
    void findById_WhenThrowNotFoundException() {
        Long id = 1L;

        when(userRepository.findById(id))
                .thenReturn(Optional.empty());

        UserNotFoundException result = assertThrows(UserNotFoundException.class, () -> userJpaAdapter.findById(id));

        assertEquals(ExceptionConstants.USER_NOT_FOUND, result.getMessage());
    }

    @Test
    void findByEmail_WhenIsSuccessful() {
        String email = "test@email.com";

        UserEntity userEntity = UserEntity.builder()
                .id(1L)
                .name("John")
                .lastName("Smith")
                .documentId("12345678")
                .email(email)
                .password("encryptedPassword")
                .role(new RoleEntity(1L, "TEST", "TEST"))
                .build();

        User mappedUser = User.builder()
                .id(1L)
                .name("John")
                .lastName("Smith")
                .documentId("12345678")
                .email(email)
                .password("encryptedPassword")
                .role(new Role(1L, "TEST", "TEST"))
                .build();

        when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(userEntity));
        when(modelMapper.map(userEntity, User.class))
                .thenReturn(mappedUser);

        User result = userJpaAdapter.findByEmail(email);

        assertNotNull(result);
        assertEquals(mappedUser.getId(), result.getId());
        assertEquals(mappedUser.getName(), result.getName());
        assertEquals(mappedUser.getLastName(), result.getLastName());
        assertEquals(mappedUser.getDocumentId(), result.getDocumentId());
        assertEquals(mappedUser.getEmail(), result.getEmail());
        assertEquals(mappedUser.getPassword(), result.getPassword());
    }

    @Test
    void findByEmail_WhenThrowNotFoundException() {
        String email = "test@email.com";

        when(userRepository.findByEmail(email))
                .thenReturn(Optional.empty());

        UserNotFoundException result = assertThrows(UserNotFoundException.class, () -> userJpaAdapter.findByEmail(email));

        assertEquals(ExceptionConstants.USER_NOT_FOUND, result.getMessage());
    }
}