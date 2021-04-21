package com.library.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//todo: Might want to return this on user creation as well.
@Data
@AllArgsConstructor
public class LoginResponse {
    private final String username;

    private final String firstName;

    private final String lastName;

    private final String email;

    private final String token;
}
