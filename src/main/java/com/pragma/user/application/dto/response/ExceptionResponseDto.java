package com.pragma.user.application.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ExceptionResponseDto {
    private String message;
    private LocalDateTime timestamp;
}
