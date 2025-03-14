package com.pragma.user.domain.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    private Long id;
    private String name;
    private String description;
}
