package com.practice.spring.Dto;


import com.practice.spring.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class AuthorCreateDto {

    private String name;
    private String email;
    private String password;

    public Author toEntity() {
        return new Author(this.name, this.email, this.password);
    }
}
