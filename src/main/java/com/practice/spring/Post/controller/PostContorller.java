package com.practice.spring.Post.controller;

import com.practice.spring.Post.dto.PostCreateDto;
import com.practice.spring.Post.dto.PostDeleteDto;
import com.practice.spring.Post.dto.PostDetailDto;
import com.practice.spring.Post.dto.PostListDto;
import com.practice.spring.Post.service.PostService;
import com.practice.spring.Common.CommonDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostContorller {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody PostCreateDto postCreateDto) {
        this.postService.save(postCreateDto);
        return new ResponseEntity<>(new CommonDto(postCreateDto, HttpStatus.CREATED.value(), "post is created"), HttpStatus.CREATED);
    }
    @GetMapping("/list")
    public ResponseEntity<?> postList(@PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)Pageable pageable) {
        Page<PostListDto> posts = this.postService.findAll(pageable);
        return new ResponseEntity<>(new CommonDto(posts, HttpStatus.OK.value(), "posts"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> postDetail(@PathVariable Long id) {
        PostDetailDto postfound = this.postService.findById(id);
        return new ResponseEntity<>(new CommonDto(postfound, HttpStatus.OK.value(), "post found"), HttpStatus.OK);
    }

    // auhtor가 쓴 글을 리스트로 표시

    @GetMapping("/author/{authorid}/list")
    public ResponseEntity<?> postListByAuthor(
            @PathVariable Long authorid,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)Pageable pageable
    ) {

        Page<PostListDto> postAuthorList = this.postService.findByAuthorId(pageable, authorid);
        return new ResponseEntity<>(new CommonDto(postAuthorList, HttpStatus.OK.value(), "postlist by author"), HttpStatus.OK);
    }
    @PatchMapping("/delete")
    public ResponseEntity<?> postDelete(@Valid @RequestBody PostDeleteDto dto) {
        this.postService.postDelete(dto);
        return new ResponseEntity<>("your post is deleted", HttpStatus.OK);
    }
}
