package com.pragma.user.application.dto.request;

import com.pragma.user.application.helper.constants.ExceptionConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoginRequestDto {

    @Email
    @NotBlank(message = ExceptionConstants.EMAIL_CANNOT_BE_BLANK)
    private String email;

    @NotBlank(message = ExceptionConstants.PASSWORD_CANNOT_BE_BLANK)
    private String password;
}
