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
    @NotNull(message = "plz write title")
    private String title;
    private String contents;
    @NotNull(message = "check authorId")
    private Long authorId;



    public  Post toEntity (Author author) {
        return Post.builder()
                .title(this.title)
                .contents(this.contents)
                .author(author)
                .delYn("N")
                .build();
    }

}
