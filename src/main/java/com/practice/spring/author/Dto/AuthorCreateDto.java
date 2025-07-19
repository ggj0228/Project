package com.practice.spring.author.Dto;


import com.practice.spring.author.domain.Author;
import com.practice.spring.author.domain.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AuthorCreateDto {

    @NotEmpty(message = "plz input name")
    private String name;
    @NotEmpty(message = "plz input email")
    private String email;
    @Size(min = 8, message = "plz input over 8")
    @NotEmpty(message = "plz input password")
    private String password;
    @Builder.Default
    private Role role = Role.USER;

    public Author toEntity() {
        return Author.builder()
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .role(this.role)
                .build();
    }
}
