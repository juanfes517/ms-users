package com.pragma.user.application.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CustomerResponseDto {

    private Long id;
    private String name;
    private String lastName;
    private String documentId;
    private String cellPhoneNumber;
    private String email;
}
