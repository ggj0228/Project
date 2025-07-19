package com.practice.spring.Post.dto;

import com.practice.spring.Post.domain.Post;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostDeleteDto {
    @NotEmpty(message = "input your email")
    private String authorEmail;
    @NotEmpty(message = "input your password")
    @Size(min = 8, message = "input over 8")
    private String authorPassword;
    @NotNull(message = "input yout postid")
    private Long postId;

}
