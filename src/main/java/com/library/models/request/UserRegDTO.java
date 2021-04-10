package com.library.models.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegDTO {

    private String patronUsername;

    private String password;

    private String firstName;

    private String lastName;

    private String email;
}
