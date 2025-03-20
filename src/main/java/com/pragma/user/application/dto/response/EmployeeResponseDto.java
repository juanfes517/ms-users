package com.pragma.user.application.dto.response;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class EmployeeResponseDto {

    private Long id;
    private String name;
    private String lastName;
    private String documentId;
    private String cellPhoneNumber;
    private String email;
}
