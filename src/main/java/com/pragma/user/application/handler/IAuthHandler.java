package com.pragma.user.application.handler;

import com.pragma.user.application.dto.request.LoginRequestDto;
import com.pragma.user.application.dto.response.AuthResponseDto;

public interface IAuthHandler {

    AuthResponseDto login(LoginRequestDto loginRequestDto);
}
