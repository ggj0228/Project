package com.practice.spring.Post.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.practice.spring.Post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // null값은 안 보이게
// jsoninclude 어노테이션을 활용해서 serislize를 포함시킬 값을 설정
public class PostListDto {
    private Long id;
    private String title;
    private String contents;
    private String authorEmail;
    private String name;

    public static PostListDto fromEntity (Post post) {
        return PostListDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .authorEmail(post.getAuthor().getEmail())
                .build();
    }

    // auhtor가 쓴 글을 리스트로 표시
    public static PostListDto fromAuthorIdEntity (Post post) {
        return PostListDto.builder()
                .id(post.getId())
                .name(post.getAuthor().getName())
                .authorEmail(post.getAuthor().getEmail())
                .title(post.getTitle())
                .contents(post.getContents())
                .build();
    }
}
