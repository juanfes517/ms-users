package com.pragma.user.application.handler;

import com.pragma.user.application.dto.request.OwnerRequestDto;
import com.pragma.user.application.dto.response.UserResponseDto;

public interface IUserHandler {

    UserResponseDto saveOwner(OwnerRequestDto ownerRequestDto);
}
