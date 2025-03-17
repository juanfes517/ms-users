package com.pragma.user.infrastructure.exceptionhandler;

import com.pragma.user.application.dto.response.ExceptionResponseDto;
import com.pragma.user.domain.exception.InvalidAgeException;
import com.pragma.user.domain.exception.InvalidCellPhoneNumberException;
import com.pragma.user.domain.exception.InvalidDocumentIdException;
import com.pragma.user.infrastructure.exception.RoleNotFoundException;
import com.pragma.user.infrastructure.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ControllerAdvisor {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidAgeException.class)
    public ExceptionResponseDto handleInvalidAgeException(InvalidAgeException e) {
        log.error(e.getMessage());
        return ExceptionResponseDto.builder()
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidCellPhoneNumberException.class)
    public ExceptionResponseDto handleInvalidCellPhoneNumberException(InvalidCellPhoneNumberException e) {
        log.error(e.getMessage());
        return ExceptionResponseDto.builder()
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidDocumentIdException.class)
    public ExceptionResponseDto handleInvalidDocumentIdException(InvalidDocumentIdException e) {
        log.error(e.getMessage());
        return ExceptionResponseDto.builder()
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ExceptionResponseDto handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.error(message);
        return ExceptionResponseDto.builder()
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RoleNotFoundException.class)
    public ExceptionResponseDto handleRoleNotFoundException(RoleNotFoundException e) {
        log.error(e.getMessage());
        return ExceptionResponseDto.builder()
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ExceptionResponseDto handleUserNotFoundException(UserNotFoundException e) {
        log.error(e.getMessage());
        return ExceptionResponseDto.builder()
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ExceptionResponseDto handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException e) {
        log.error(e.getMessage());
        return ExceptionResponseDto.builder()
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
