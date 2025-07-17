package com.practice.spring.Post.dto;

import com.practice.spring.Post.domain.Post;
import com.practice.spring.author.domain.Author;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class PostCreateDto {
    @NotNull
    private String title;
    private String contents;
    @NotNull
    private Long authorId;


    public  Post toEntity (Author author) {
        return Post.builder()
                .title(this.title)
                .contents(this.contents)
                .author(author)
                .build();
    }

}
