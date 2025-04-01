package com.pragma.user.infrastructure.input.rest;

import com.pragma.user.application.dto.request.LoginRequestDto;
import com.pragma.user.application.dto.response.AuthResponseDto;
import com.pragma.user.application.handler.IAuthHandler;
import com.pragma.user.infrastructure.helper.constants.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.AUTH_CONTROLLER)
@RequiredArgsConstructor
public class AuthController {

    private final IAuthHandler authHandler;

    @Operation(summary = ApiConstants.LOGIN_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiConstants.OK_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = "400", description = ApiConstants.BAD_REQUEST_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = "403", description = ApiConstants.FORBIDDEN_DESCRIPTION, content = @Content),
            @ApiResponse(responseCode = "404", description = ApiConstants.NOT_FOUND_DESCRIPTION, content = @Content),
    })
    @PostMapping(ApiConstants.LOGIN_ENDPOINT)
    public ResponseEntity<AuthResponseDto> login (@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authHandler.login(loginRequestDto));
    }
}
