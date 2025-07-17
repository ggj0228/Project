package com.practice.spring.Post.dto;


import com.practice.spring.Post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostListDto {
    private Long id;
    private String title;

    public static PostListDto fromEntity (Post post) {
        return PostListDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .build();
    }
}
