package com.practice.spring.author.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class AuthorUpdatePwDto {
    @NotEmpty(message = "plz input email")
    private String email;
    @Size(min = 8, message = "plz input over 8")
    @NotEmpty(message = "plz input your current password")
    private String password;
    @Size(min = 8, message = "plz input over 8")
    @NotEmpty(message = "plz input your new password")
    private String newPassword;
}
