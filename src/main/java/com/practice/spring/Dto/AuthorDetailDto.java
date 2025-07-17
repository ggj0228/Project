package com.practice.spring.Dto;

import com.practice.spring.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class AuthorDetailDto {
    private Long id;
    private String name;
    private String password;

    public static AuthorDetailDto fromDetailDto(Author author) {
        return new AuthorDetailDto(author.getId(), author.getName(), author.getPassword());
    }
}
