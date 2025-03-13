package com.pragma.user.domain.model;

import com.pragma.user.domain.exception.InvalidCellPhoneNumberException;
import com.pragma.user.domain.exception.InvalidDocumentIdException;
import com.pragma.user.domain.helper.constants.ExceptionConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void validateCellPhoneNumberWhenIsValidWithoutPlusSymbol() {
        // Arrange
        User user = new User();
        user.setCellPhoneNumber("571232341543");

        // Act
        boolean result = user.validateCellPhoneNumber();

        // Assert
        assertTrue(result);
    }

    @Test
    void validateCellPhoneNumberWhenIsValidWithPlusSymbol() {
        // Arrange
        User user = new User();
        user.setCellPhoneNumber("+571232341543");

        // Act
        boolean result = user.validateCellPhoneNumber();

        // Assert
        assertTrue(result);
    }

    @Test
    void validateCellPhoneNumberWhenItHas14Characters() {
        // Arrange
        User user = new User();
        user.setCellPhoneNumber("+5712323415431");

        // Act
        InvalidCellPhoneNumberException exception = assertThrows(InvalidCellPhoneNumberException.class, user::validateCellPhoneNumber);

        // Assert
        assertEquals(ExceptionConstants.CELL_PHONE_NUMBER_LENGTH_EXCEPTION_MESSAGE, exception.getMessage());
    }

    @Test
    void validateCellPhoneNumberWhenItHasInvalidCharacters() {
        // Arrange
        User user = new User();
        user.setCellPhoneNumber("+571232sdfasd");

        // Act
        InvalidCellPhoneNumberException exception = assertThrows(InvalidCellPhoneNumberException.class, user::validateCellPhoneNumber);

        // Assert
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

        // Act
        InvalidDocumentIdException exception = assertThrows(InvalidDocumentIdException.class, user::validateDocumentId);

        // Assert
        assertEquals(ExceptionConstants.DOCUMENT_ID_IS_NOT_A_NUMBER_MESSAGE, exception.getMessage());
    }
}