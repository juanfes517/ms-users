package com.pragma.user.infrastructure.helper.constants;

public class ApiConstants {

    private ApiConstants() {}

    public static final String AUTH_CONTROLLER = "/api/v1/auth";
    public static final String LOGIN_ENDPOINT = "/login";

    public static final String USER_CONTROLLER = "/api/v1/users";
    public static final String SAVE_OWNER_ENDPOINT = "/owner";
    public static final String SAVE_EMPLOYEE_ENDPOINT = "/employee";
    public static final String SAVE_CUSTOMER_ENDPOINT = "/customer";
    public static final String USER_HAS_ROLE_ENDPOINT = "/{user-id}/has-role";
    public static final String USER_HAS_EMAIL_ENDPOINT = "/{user-id}/has-email";
    public static final String GET_USER_ID_BY_EMAIL_ENDPOINT = "/id";
    public static final String GET_EMAIL_BY_USER_ID_ENDPOINT = "/email";
    public static final String GET_CELL_PHONE_NUMBER_BY_ID_ENDPOINT = "/{id}/cell-phone-number";


    public static final String SAVE_OWNER_DESCRIPTION = "Save a new owner user";
    public static final String SAVE_EMPLOYEE_DESCRIPTION = "Save a new employee user";
    public static final String SAVE_CUSTOMER_DESCRIPTION = "Save a new customer user";
    public static final String USER_HAS_ROLE_DESCRIPTION = "Check if user has role";
    public static final String USER_HAS_EMAIL_DESCRIPTION = "Check if user has an email";
    public static final String USER_ID_BY_EMAIL_DESCRIPTION = "Find the user id by the email";
    public static final String USER_EMAIL_BY_ID_DESCRIPTION = "Find the user email by the id";
    public static final String CELL_PHONE_NUMBER_BY_ID_DESCRIPTION = "Find the user id by the email";
    public static final String LOGIN_DESCRIPTION = "Login to the system";

    public static final String OK_DESCRIPTION = "Request successful";
    public static final String SUCCESS_DESCRIPTION = "Request succeeded";
    public static final String FORBIDDEN_DESCRIPTION = "Invalid credentials";
    public static final String OBJECT_CREATED_DESCRIPTION = "Object created";
    public static final String BAD_REQUEST_DESCRIPTION = "Bad request";
    public static final String NOT_FOUND_DESCRIPTION = "Object not found";
    public static final String CONFLICT_DESCRIPTION = "Conflict occurred";
}
