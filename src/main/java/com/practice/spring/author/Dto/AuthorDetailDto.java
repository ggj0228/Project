package com.practice.spring.author.Dto;

import com.practice.spring.author.domain.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class AuthorDetailDto {
    private Long id;
    private String name;
    private String password;

    public static AuthorDetailDto fromDetailDto(Author author) {
        return AuthorDetailDto.builder()
                .id(author.getId())
                .name(author.getName())
                .password(author.getPassword())
                .build();
    }
}
