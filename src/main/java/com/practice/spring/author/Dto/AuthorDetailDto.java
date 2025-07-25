package com.practice.spring.author.Dto;

import com.practice.spring.Post.domain.Post;
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
    private Integer postCount;
    private String lastPostTitle;

    public static AuthorDetailDto fromDetailDto(Author author) {
        String lastTitle = author.getPostList().isEmpty() ? "not posting yet"
                : author.getPostList().get(author.getPostList().size()-1).getTitle();
        return AuthorDetailDto.builder()
                .id(author.getId())
                .name(author.getName())
                .password(author.getPassword())
                .postCount(author.getPostList().size())
                .lastPostTitle(lastTitle)
                .build();
    }
}
