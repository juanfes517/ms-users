package com.pragma.user.application.handler.impl;

import com.pragma.user.application.dto.request.CustomerRequestDto;
import com.pragma.user.application.dto.request.EmployeeRequestDto;
import com.pragma.user.application.dto.request.OwnerRequestDto;
import com.pragma.user.application.dto.response.CustomerResponseDto;
import com.pragma.user.application.dto.response.EmployeeResponseDto;
import com.pragma.user.application.dto.response.OwnerResponseDto;
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
    void saveOwner_WhenIsValid() {
        OwnerRequestDto ownerRequestDto = OwnerRequestDto.builder()
                .name("Paco")
                .lastName("Torres")
                .password("password")
                .build();

        Role role = new Role(1L, "OWNER", "testDescription");

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

        OwnerResponseDto ownerResponseDto = OwnerResponseDto.builder()
                .id(1L)
                .name("Paco")
                .lastName("Torres")
                .build();

        when(passwordEncoder.encode(ownerRequestDto.getPassword()))
                .thenReturn("encryptedPassword");
        when(roleServicePort.findRoleByName("OWNER"))
                .thenReturn(role);
        when(modelMapper.map(ownerRequestDto, User.class))
                .thenReturn(userMapped);
        when(userServicePort.saveOwner(userMapped))
                .thenReturn(userSaved);
        when(modelMapper.map(userSaved, OwnerResponseDto.class))
                .thenReturn(ownerResponseDto);

        OwnerResponseDto result = userHandler.saveOwner(ownerRequestDto);

        assertNotNull(ownerResponseDto);
        assertEquals("Paco", result.getName());
        assertEquals("Torres", result.getLastName());
    }

    @Test
    void saveEmployee_WhenIsSuccessful() {
        EmployeeRequestDto employeeRequestDto = EmployeeRequestDto.builder()
                .name("test name")
                .lastName("test last name")
                .documentId("11111111")
                .cellPhoneNumber("123332112")
                .email("test@email.com")
                .password("password")
                .roleId(1L)
                .build();

        Role role = new Role(1L, "ROLE_EMPLOYEE", "testDescription");

        User mappedUser = User.builder()
                .id(null)
                .name("test name")
                .lastName("test last name")
                .documentId("11111111")
                .cellPhoneNumber("123332112")
                .email("test@email.com")
                .password("encrypted-password")
                .role(role)
                .build();

        User savedUser = User.builder()
                .id(1L)
                .name("test name")
                .lastName("test last name")
                .documentId("11111111")
                .cellPhoneNumber("123332112")
                .email("test@email.com")
                .password("encrypted-password")
                .role(role)
                .build();

        EmployeeResponseDto employeeResponseDto = EmployeeResponseDto.builder()
                .id(1L)
                .name("test name")
                .lastName("test last name")
                .documentId("11111111")
                .cellPhoneNumber("123332112")
                .email("test@email.com")
                .build();

        when(passwordEncoder.encode(employeeRequestDto.getPassword()))
                .thenReturn("encrypted-password");
        when(roleServicePort.findRoleByName("EMPLOYEE"))
                .thenReturn(role);
        when(modelMapper.map(employeeRequestDto, User.class))
                .thenReturn(mappedUser);
        when(userServicePort.saveEmployee(mappedUser))
                .thenReturn(savedUser);
        when(modelMapper.map(savedUser, EmployeeResponseDto.class))
                .thenReturn(employeeResponseDto);

        EmployeeResponseDto result = userHandler.saveEmployee(employeeRequestDto);

        assertNotNull(result);
        assertEquals(employeeResponseDto.getId(), result.getId());
        assertEquals(employeeResponseDto.getName(), result.getName());
        assertEquals(employeeResponseDto.getLastName(), result.getLastName());
        assertEquals(employeeResponseDto.getDocumentId(), result.getDocumentId());
        assertEquals(employeeResponseDto.getCellPhoneNumber(), result.getCellPhoneNumber());
        assertEquals(employeeResponseDto.getEmail(), result.getEmail());
    }

    @Test
    void saveCustomer_WhenIsSuccessful() {
        CustomerRequestDto customerRequestDto = CustomerRequestDto.builder()
                .name("test name")
                .lastName("test last name")
                .documentId("11111111")
                .cellPhoneNumber("123332112")
                .email("test@email.com")
                .password("password")
                .roleId(1L)
                .build();

        Role role = new Role(1L, "CUSTOMER", "testDescription");

        User mappedUser = User.builder()
                .id(null)
                .name("test name")
                .lastName("test last name")
                .documentId("11111111")
                .cellPhoneNumber("123332112")
                .email("test@email.com")
                .password("encrypted-password")
                .role(role)
                .build();

        User savedUser = User.builder()
                .id(1L)
                .name("test name")
                .lastName("test last name")
                .documentId("11111111")
                .cellPhoneNumber("123332112")
                .email("test@email.com")
                .password("encrypted-password")
                .role(role)
                .build();

        CustomerResponseDto customerResponseDto = CustomerResponseDto.builder()
                .id(1L)
                .name("test name")
                .lastName("test last name")
                .documentId("11111111")
                .cellPhoneNumber("123332112")
                .email("test@email.com")
                .build();

        when(passwordEncoder.encode(customerRequestDto.getPassword()))
                .thenReturn("encrypted-password");
        when(roleServicePort.findRoleByName("CUSTOMER"))
                .thenReturn(role);
        when(modelMapper.map(customerRequestDto, User.class))
                .thenReturn(mappedUser);
        when(userServicePort.saveCustomer(mappedUser))
                .thenReturn(savedUser);
        when(modelMapper.map(savedUser, CustomerResponseDto.class))
                .thenReturn(customerResponseDto);

        CustomerResponseDto result = userHandler.saveCustomer(customerRequestDto);

        assertNotNull(result);
        assertEquals(customerResponseDto.getId(), result.getId());
        assertEquals(customerResponseDto.getName(), result.getName());
        assertEquals(customerResponseDto.getLastName(), result.getLastName());
        assertEquals(customerResponseDto.getDocumentId(), result.getDocumentId());
        assertEquals(customerResponseDto.getCellPhoneNumber(), result.getCellPhoneNumber());
        assertEquals(customerResponseDto.getEmail(), result.getEmail());
    }

    @Test
    void userHasRole_ShouldReturnTrue_WhenRoleNameMatches() {
        Long userId = 1L;
        String roleName = "ROLE_OWNER";

        when(userServicePort.userHasRole(userId, roleName)).thenReturn(true);

        boolean result = userHandler.userHasRole(userId, roleName);

        assertTrue(result);
    }

    @Test
    void userHasRole_ShouldReturnFalse_WhenRoleNameDoesNotMatch() {
        Long userId = 1L;
        String roleName = "ROLE_EMPLOYEE";

        when(userServicePort.userHasRole(userId, roleName)).thenReturn(false);

        boolean result = userHandler.userHasRole(userId, roleName);

        assertFalse(result);
    }

    @Test
    void userHasEmail_ShouldReturnTrue_WhenEmailMatches() {
        Long userId = 1L;
        String userEmail = "testTrue@email.com";

        when(userServicePort.userHasEmail(userId, userEmail)).thenReturn(true);

        boolean result = userHandler.userHasEmail(userId, userEmail);

        assertTrue(result);
    }

    @Test
    void userHasRole_ShouldReturnFalse_WhenEmailDoesNotMatch() {
        Long userId = 1L;
        String userEmail = "testFalse@email.com";

        when(userServicePort.userHasEmail(userId, userEmail)).thenReturn(false);

        boolean result = userHandler.userHasEmail(userId, userEmail);

        assertFalse(result);
    }

    @Test
    void findUserIdByEmail() {
        String email = "test@email.com";
        Long userId = 1L;

        when(userServicePort.findUserIdByEmail(email))
                .thenReturn(userId);

        Long result = userHandler.findUserIdByEmail(email);

        assertNotNull(result);
        assertEquals(userId, result);
    }

    @Test
    void findCellPhoneNumberById_WhenIsSuccessful() {
        Long userId = 1L;
        String cellPhoneNumber = "22222222";

        when(userServicePort.findCellPhoneNumberById(userId))
                .thenReturn(cellPhoneNumber);

        String result = userHandler.findCellPhoneNumberById(userId);

        assertNotNull(result);
        assertEquals(cellPhoneNumber, result);
    }
}