package com.pragma.user.domain.model;

import com.pragma.user.domain.exception.InvalidAgeExeption;
import com.pragma.user.domain.exception.InvalidCellPhoneNumberException;
import com.pragma.user.domain.exception.InvalidDocumentIdException;
import com.pragma.user.domain.helper.constants.ExceptionConstants;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String name;
    private String lastName;
    private String documentId;
    private String cellPhoneNumber;
    private LocalDate dateOfBirth;
    private String email;
    private String password;
    private Role role;

    public boolean validateCellPhoneNumber() {
        String cellPhoneNumberOfValidation = this.cellPhoneNumber;

        if (cellPhoneNumberOfValidation.length() > 13) {
            throw new InvalidCellPhoneNumberException(ExceptionConstants.CELL_PHONE_NUMBER_LENGTH_EXCEPTION_MESSAGE);
        }

        if (cellPhoneNumberOfValidation.charAt(0) == '+') {
            cellPhoneNumberOfValidation = cellPhoneNumberOfValidation.substring(1);
        }

        if (!cellPhoneNumberOfValidation.chars().allMatch(Character::isDigit)) {
            throw new InvalidCellPhoneNumberException(ExceptionConstants.CELL_PHONE_NUMBER_IS_NOT_A_NUMBER_MESSAGE);
        }

        return true;
    }

    public boolean validateDocumentId() {
        if (!this.documentId.chars().allMatch(Character::isDigit)) {
            throw new InvalidDocumentIdException(ExceptionConstants.DOCUMENT_ID_IS_NOT_A_NUMBER_MESSAGE);
        }

        return true;
    }

    public boolean validateAge() {
        int age = Period.between(this.dateOfBirth, LocalDate.now()).getYears();

        if (age < 18) {
            throw  new InvalidAgeExeption(ExceptionConstants.LEGAL_AGE_MESSAGE);
        }

        return true;
    }
}
