package com.pragma.user.application.handler;

import com.pragma.user.application.dto.request.EmployeeRequestDto;
import com.pragma.user.application.dto.request.OwnerRequestDto;
import com.pragma.user.application.dto.response.EmployeeResponseDto;
import com.pragma.user.application.dto.response.OwnerResponseDto;

public interface IUserHandler {

    OwnerResponseDto saveOwner(OwnerRequestDto ownerRequestDto);

    EmployeeResponseDto saveEmployee(EmployeeRequestDto employeeRequestDto);

    boolean userHasRole(Long userId, String roleName);

    boolean userHasEmail(Long userId, String email);
}
