package com.library.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

//todo: Might want to return this on user creation as well.
@Data
@AllArgsConstructor
public class LoginResponseDTO {
    private final String username;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String token;
    private final String role;
}
