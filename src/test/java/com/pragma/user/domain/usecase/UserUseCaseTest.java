package com.pragma.user.domain.usecase;

import com.pragma.user.domain.model.Role;
import com.pragma.user.domain.model.User;
import com.pragma.user.domain.spi.IFoodCourtExternalService;
import com.pragma.user.domain.spi.IJwtSecurityServicePort;
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

    @Mock
    private IFoodCourtExternalService foodCourtExternalService;

    @Mock
    private IJwtSecurityServicePort jwtSecurityServicePort;

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

    @Test
    void saveEmployee_WhenIsSuccessful(){
        String tokenEmail = "test@mail.com";

        User ownerUser = User.builder()
                .id(1L)
                .name("Jonh")
                .lastName("Smith")
                .documentId("4345345")
                .cellPhoneNumber("+571234567890")
                .email("jonh@mail.com")
                .password("encrypted-password")
                .role(new Role(1L, "ROLE_OWNER", "restaurant owner"))
                .build();

        User user = User.builder()
                .name("Pedro")
                .lastName("Lopez")
                .documentId("1234567890")
                .cellPhoneNumber("+571234567890")
                .email("pedro@mail.com")
                .password("encrypted-password")
                .role(new Role(1L, "ROLE_EMPLOYEE", "restaurant employee"))
                .build();

        User savedEmployee = User.builder()
                .id(2L)
                .name("Pedro")
                .lastName("Lopez")
                .documentId("1234567890")
                .cellPhoneNumber("+571234567890")
                .email("pedro@mail.com")
                .password("encrypted-password")
                .role(new Role(1L, "ROLE_EMPLOYEE", "restaurant employee"))
                .build();

        when(jwtSecurityServicePort.getSubject())
                .thenReturn(tokenEmail);
        when(userPersistencePort.findByEmail(tokenEmail))
                .thenReturn(ownerUser);
        when(userPersistencePort
                .save(user)).thenReturn(savedEmployee);

        User result = userUseCase.saveEmployee(user);

        assertNotNull(result);
        assertEquals(savedEmployee.getName(), result.getName());
        assertEquals(savedEmployee.getLastName(), result.getLastName());
        assertEquals(savedEmployee.getDocumentId(), result.getDocumentId());
        assertEquals(savedEmployee.getCellPhoneNumber(), result.getCellPhoneNumber());
        assertEquals(savedEmployee.getEmail(), result.getEmail());
        assertEquals(savedEmployee.getPassword(), result.getPassword());
        assertEquals(savedEmployee.getRole().getName(), result.getRole().getName());
    }

    @Test
    void saveCustomer_WhenIsSuccessful(){
        User user = User.builder()
                .name("Pedro")
                .lastName("Lopez")
                .documentId("1234567890")
                .cellPhoneNumber("+571234567890")
                .email("pedro@mail.com")
                .password("encrypted-password")
                .role(new Role(1L, "ROLE_CUSTOMER", "restaurant employee"))
                .build();

        User savedUser = User.builder()
                .id(1L)
                .name("Pedro")
                .lastName("Lopez")
                .documentId("1234567890")
                .cellPhoneNumber("+571234567890")
                .email("pedro@mail.com")
                .password("encrypted-password")
                .role(new Role(1L, "ROLE_CUSTOMER", "restaurant employee"))
                .build();

        when(userPersistencePort.save(user))
                .thenReturn(savedUser);

        User result = userUseCase.saveCustomer(user);

        assertNotNull(result);
        assertEquals(savedUser.getId(), result.getId());
        assertEquals(savedUser.getName(), result.getName());
        assertEquals(savedUser.getLastName(), result.getLastName());
        assertEquals(savedUser.getDocumentId(), result.getDocumentId());
        assertEquals(savedUser.getCellPhoneNumber(), result.getCellPhoneNumber());
        assertEquals(savedUser.getEmail(), result.getEmail());
        assertEquals(savedUser.getPassword(), result.getPassword());
        assertEquals(savedUser.getRole().getName(), result.getRole().getName());
    }
}