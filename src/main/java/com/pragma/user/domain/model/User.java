package com.pragma.user.domain.model;

import lombok.*;

import java.time.LocalDate;

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
}
