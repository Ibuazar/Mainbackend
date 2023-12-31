package com.sergio.jwt.backend.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {

    @NotEmpty
    private String fullName;

    private String phoneNumber;

    @NotEmpty
    private String login;

    @NotEmpty
    private char[] password;

    @NotEmpty
    private String role;

}
