package com.pragma.user.application.dto.request;

import com.pragma.user.application.helper.constants.ExceptionConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class OwnerRequestDto {

    @NotBlank(message = ExceptionConstants.NAME_CANNOT_BE_BLANK)
    private String name;

    @NotBlank(message = ExceptionConstants.LAST_NAME_CANNOT_BE_BLANK)
    private String lastName;

    @NotBlank(message = ExceptionConstants.DOCUMENT_ID_CANNOT_BE_BLANK)
    private String documentId;

    @NotBlank(message = ExceptionConstants.CELL_PHONE_NUMBER_CANNOT_BE_BLANK)
    private String cellPhoneNumber;

    @NotNull(message = ExceptionConstants.DATE_OF_BIRTH_CANNOT_BE_BLANK)
    private LocalDate dateOfBirth;

    @Email
    @NotBlank(message = ExceptionConstants.EMAIL_CANNOT_BE_BLANK)
    private String email;

    @NotBlank(message = ExceptionConstants.PASSWORD_CANNOT_BE_BLANK)
    private String password;
}
