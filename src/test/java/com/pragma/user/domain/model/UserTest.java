package com.pragma.user.domain.model;

import com.pragma.user.domain.exception.InvalidAgeExeption;
import com.pragma.user.domain.exception.InvalidCellPhoneNumberException;
import com.pragma.user.domain.exception.InvalidDocumentIdException;
import com.pragma.user.domain.helper.constants.ExceptionConstants;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void validateCellPhoneNumberWhenIsValidWithoutPlusSymbol() {
        User user = new User();
        user.setCellPhoneNumber("571232341543");

        boolean result = user.validateCellPhoneNumber();

        assertTrue(result);
    }

    @Test
    void validateCellPhoneNumberWhenIsValidWithPlusSymbol() {
        User user = new User();
        user.setCellPhoneNumber("+571232341543");

        boolean result = user.validateCellPhoneNumber();

        assertTrue(result);
    }

    @Test
    void validateCellPhoneNumberWhenItHas14Characters() {
        User user = new User();
        user.setCellPhoneNumber("+5712323415431");

        InvalidCellPhoneNumberException exception = assertThrows(InvalidCellPhoneNumberException.class, user::validateCellPhoneNumber);

        assertEquals(ExceptionConstants.CELL_PHONE_NUMBER_LENGTH_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void validateCellPhoneNumberWhenItHasInvalidCharacters() {
        User user = new User();
        user.setCellPhoneNumber("+571232sdfasd");

        InvalidCellPhoneNumberException exception = assertThrows(InvalidCellPhoneNumberException.class, user::validateCellPhoneNumber);

        assertEquals(ExceptionConstants.CELL_PHONE_NUMBER_IS_NOT_A_NUMBER_MESSAGE, exception.getMessage());
    }

    @Test
    void validateDocumentIdWhenIsValid() {
        User user = new User();
        user.setDocumentId("123456789");

        boolean result = user.validateDocumentId();

        assertTrue(result);
    }

    @Test
    void validateDocumentIdWhenItHasInvalidCharacters() {
        User user = new User();
        user.setDocumentId("asfrdsasd");

        InvalidDocumentIdException exception = assertThrows(InvalidDocumentIdException.class, user::validateDocumentId);

        assertEquals(ExceptionConstants.DOCUMENT_ID_IS_NOT_A_NUMBER_MESSAGE, exception.getMessage());
    }

    @Test
    void validateAgeWhenIsValid() {
        User user = new User();
        user.setDateOfBirth(LocalDate.of(2000, 5, 17));

        boolean result = user.validateAge();

        assertTrue(result);
    }

    @Test
    void validateAgeWhenItHasIlegalAge() {
        User user = new User();
        int actualYear = LocalDate.now().getYear();
        user.setDateOfBirth(LocalDate.of(actualYear - 10, 5, 17));

        InvalidAgeExeption exeption = assertThrows(InvalidAgeExeption.class, user::validateAge);

        assertEquals(ExceptionConstants.LEGAL_AGE_MESSAGE, exeption.getMessage());
    }
}