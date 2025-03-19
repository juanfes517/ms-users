package com.pragma.user.application.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AuthResponseDto {

    private String email;
    private String accessToken;
}
