package com.pragma.user.application.handler;

import com.pragma.user.application.dto.request.OwnerRequestDto;
import com.pragma.user.application.dto.response.OwnerResponseDto;

public interface IUserHandler {

    OwnerResponseDto saveOwner(OwnerRequestDto ownerRequestDto);

    boolean userHasRole(Long userId, String roleName);

    boolean userHasEmail(Long userId, String email);
}
