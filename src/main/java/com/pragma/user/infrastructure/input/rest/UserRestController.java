package com.pragma.user.infrastructure.input.rest;

import com.pragma.user.application.dto.request.OwnerRequestDto;
import com.pragma.user.application.dto.response.UserResponseDto;
import com.pragma.user.application.handler.IUserHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final IUserHandler userHandler;

    @PostMapping
    public ResponseEntity<UserResponseDto> saveUserOwner(@Valid @RequestBody OwnerRequestDto ownerRequestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userHandler.saveOwner(ownerRequestDto));
    }
}
