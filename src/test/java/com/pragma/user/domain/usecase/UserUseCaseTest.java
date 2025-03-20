package com.pragma.user.domain.usecase;

import com.pragma.user.domain.model.Role;
import com.pragma.user.domain.model.User;
import com.pragma.user.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @InjectMocks
    private UserUseCase userUseCase;

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Test
    void saveOwner() {
        // Arrange
        Role role = Role.builder()
                .id(1L)
                .name("ROLE_OWNER")
                .description("restaurant owner")
                .build();

        User user = User.builder()
                .id(1L)
                .name("Pedro")
                .lastName("Lopez")
                .documentId("1234567890")
                .cellPhoneNumber("+571234567890")
                .dateOfBirth(LocalDate.of(1999, 3, 13))
                .email("pedro@mail.com")
                .password("123456")
                .role(role)
                .build();

        when(userPersistencePort.save(any(User.class))).thenReturn(user);

        // Act
        User savedUser = userUseCase.saveOwner(user);

        // Assert
        assertNotNull(savedUser);
        assertEquals(user.getId(), savedUser.getId());
        assertEquals(user.getName(), savedUser.getName());
        assertEquals(user.getLastName(), savedUser.getLastName());
        assertEquals(user.getDocumentId(), savedUser.getDocumentId());
        assertEquals(user.getCellPhoneNumber(), savedUser.getCellPhoneNumber());
        assertEquals(user.getDateOfBirth(), savedUser.getDateOfBirth());
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getPassword(), savedUser.getPassword());
        assertEquals(user.getRole().getName(), savedUser.getRole().getName());
    }

    @Test
    void userHasRole_returnsTrue_whenUserHasOwnerRole() {
        Long userId = 1L;
        String roleName = "ROLE_OWNER";

        Role role = Role.builder()
                .id(1L)
                .name("ROLE_OWNER")
                .description("restaurant owner")
                .build();

        User user = User.builder()
                .id(1L)
                .name("Pedro")
                .lastName("Lopez")
                .documentId("1234567890")
                .cellPhoneNumber("+571234567890")
                .dateOfBirth(LocalDate.of(1999, 3, 13))
                .email("pedro@mail.com")
                .password("123456")
                .role(role)
                .build();

        when(userPersistencePort.findById(userId))
                .thenReturn(user);

        boolean result = userUseCase.userHasRole(userId, roleName);

        assertTrue(result);
    }

    @Test
    void userHasRole_ReturnsTrue_WhenUserDosNotHaveOwnerRole() {
        Long userId = 1L;
        String roleName = "ROLE_OWNER";

        Role role = Role.builder()
                .id(1L)
                .name("ROLE_EMPLOYEE")
                .description("restaurant owner")
                .build();

        User user = User.builder()
                .id(1L)
                .name("Pedro")
                .lastName("Lopez")
                .documentId("1234567890")
                .cellPhoneNumber("+571234567890")
                .dateOfBirth(LocalDate.of(1999, 3, 13))
                .email("pedro@mail.com")
                .password("123456")
                .role(role)
                .build();

        when(userPersistencePort.findById(userId))
                .thenReturn(user);

        boolean result = userUseCase.userHasRole(userId, roleName);

        assertFalse(result);
    }

    @Test
    void userHasEmail_ReturnsTrue_WhenUserHasEmail() {
        Long userId = 1L;
        String email = "test@mail.com";

        Role role = Role.builder()
                .id(1L)
                .name("ROLE_OWNER")
                .description("restaurant owner")
                .build();

        User user = User.builder()
                .id(1L)
                .name("Pedro")
                .lastName("Lopez")
                .documentId("1234567890")
                .cellPhoneNumber("+571234567890")
                .dateOfBirth(LocalDate.of(1999, 3, 13))
                .email("test@mail.com")
                .password("123456")
                .role(role)
                .build();

        when(userPersistencePort.findById(userId))
                .thenReturn(user);

        boolean result = userUseCase.userHasEmail(userId, email);

        assertTrue(result);
    }

    @Test
    void userHasEmail_ReturnsTrue_WhenUserDoesNotHaveEmail() {
        Long userId = 1L;
        String email = "test@mail.com";

        Role role = Role.builder()
                .id(1L)
                .name("ROLE_OWNER")
                .description("restaurant owner")
                .build();

        User user = User.builder()
                .id(1L)
                .name("Pedro")
                .lastName("Lopez")
                .documentId("1234567890")
                .cellPhoneNumber("+571234567890")
                .dateOfBirth(LocalDate.of(1999, 3, 13))
                .email("other@mail.com")
                .password("123456")
                .role(role)
                .build();

        when(userPersistencePort.findById(userId))
                .thenReturn(user);

        boolean result = userUseCase.userHasEmail(userId, email);

        assertFalse(result);
    }
}