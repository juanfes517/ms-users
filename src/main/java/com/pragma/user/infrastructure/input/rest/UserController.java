package com.pragma.user.infrastructure.input.rest;

import com.pragma.user.application.dto.request.CustomerRequestDto;
import com.pragma.user.application.dto.request.EmployeeRequestDto;
import com.pragma.user.application.dto.request.OwnerRequestDto;
import com.pragma.user.application.dto.response.CustomerResponseDto;
import com.pragma.user.application.dto.response.EmployeeResponseDto;
import com.pragma.user.application.dto.response.OwnerResponseDto;
import com.pragma.user.application.handler.IUserHandler;
import com.pragma.user.infrastructure.helper.constants.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstants.USER_CONTROLLER)
@RequiredArgsConstructor
public class UserController {

    private final IUserHandler userHandler;

    @Operation(summary = ApiConstants.SAVE_OWNER_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = ApiConstants.OBJECT_CREATED_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = "400", description = ApiConstants.BAD_REQUEST_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = "404", description = ApiConstants.NOT_FOUND_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = "409", description = ApiConstants.CONFLICT_DESCRIPTION, content = @Content)
    })
    @PostMapping(ApiConstants.SAVE_OWNER_ENDPOINT)
    public ResponseEntity<OwnerResponseDto> saveOwner(@Valid @RequestBody OwnerRequestDto ownerRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userHandler.saveOwner(ownerRequestDto));
    }

    @Operation(summary = ApiConstants.SAVE_EMPLOYEE_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = ApiConstants.OBJECT_CREATED_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = "400", description = ApiConstants.BAD_REQUEST_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = "404", description = ApiConstants.NOT_FOUND_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = "409", description = ApiConstants.CONFLICT_DESCRIPTION, content = @Content)
    })
    @PostMapping(ApiConstants.SAVE_EMPLOYEE_ENDPOINT)
    public ResponseEntity<EmployeeResponseDto> saveEmployee(@Valid @RequestBody EmployeeRequestDto employeeRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userHandler.saveEmployee(employeeRequestDto));
    }

    @Operation(summary = ApiConstants.SAVE_CUSTOMER_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = ApiConstants.OBJECT_CREATED_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = "400", description = ApiConstants.BAD_REQUEST_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = "404", description = ApiConstants.NOT_FOUND_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = "409", description = ApiConstants.CONFLICT_DESCRIPTION, content = @Content)
    })
    @PostMapping(ApiConstants.SAVE_CUSTOMER_ENDPOINT)
    public ResponseEntity<CustomerResponseDto> saveCustomer(@Valid @RequestBody CustomerRequestDto customerRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userHandler.saveCustomer(customerRequestDto));
    }

    @Operation(summary = ApiConstants.USER_HAS_ROLE_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiConstants.SUCCESS_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = "404", description = ApiConstants.NOT_FOUND_DESCRIPTION, content = @Content)
    })
    @GetMapping(ApiConstants.USER_HAS_ROLE_ENDPOINT)
    public ResponseEntity<Boolean> userHasRole(@PathVariable("user-id") Long userId, @RequestParam String roleName) {
        return ResponseEntity.ok(userHandler.userHasRole(userId, roleName));
    }

    @Operation(summary = ApiConstants.USER_HAS_EMAIL_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiConstants.SUCCESS_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = "404", description = ApiConstants.NOT_FOUND_DESCRIPTION, content = @Content)
    })
    @GetMapping(ApiConstants.USER_HAS_EMAIL_ENDPOINT)
    public ResponseEntity<Boolean> userHasEmail(@PathVariable("user-id") Long userId, @RequestParam String email) {
        return ResponseEntity.ok(userHandler.userHasEmail(userId, email));
    }

    @Operation(summary = ApiConstants.USER_ID_BY_EMAIL_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiConstants.SUCCESS_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = "404", description = ApiConstants.NOT_FOUND_DESCRIPTION, content = @Content)
    })
    @GetMapping(ApiConstants.GET_USER_ID_BY_EMAIL_ENDPOINT)
    public ResponseEntity<Long> getUserIdByEmail(@RequestParam String email) {
        return ResponseEntity.ok(userHandler.findUserIdByEmail(email));
    }

    @Operation(summary = ApiConstants.USER_EMAIL_BY_ID_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiConstants.SUCCESS_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = "404", description = ApiConstants.NOT_FOUND_DESCRIPTION, content = @Content)
    })
    @GetMapping(ApiConstants.GET_EMAIL_BY_USER_ID_ENDPOINT)
    public ResponseEntity<String> getEmailByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(userHandler.findEmailByUserId(userId));
    }

    @Operation(summary = ApiConstants.CELL_PHONE_NUMBER_BY_ID_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiConstants.SUCCESS_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = "404", description = ApiConstants.NOT_FOUND_DESCRIPTION, content = @Content)
    })
    @GetMapping(ApiConstants.GET_CELL_PHONE_NUMBER_BY_ID_ENDPOINT)
    public ResponseEntity<String> getCellPhoneNumberById(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(userHandler.findCellPhoneNumberById(userId));
    }
}
