package com.practice.spring.Post.service;

import com.practice.spring.Post.domain.Post;
import com.practice.spring.Post.dto.PostCreateDto;
import com.practice.spring.Post.dto.PostDetailDto;
import com.practice.spring.Post.dto.PostListDto;
import com.practice.spring.Post.repository.PostRepository;
import com.practice.spring.author.Dto.AuthorDetailDto;
import com.practice.spring.author.Dto.AuthorListDto;
import com.practice.spring.author.Repository.AuthorRepository;
import com.practice.spring.author.domain.Author;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional


public class PostService {

    private final PostRepository postRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public PostService(PostRepository postRepository, AuthorRepository authorRepository) {
        this.postRepository = postRepository;
        this.authorRepository = authorRepository;
    }
    @Transactional(readOnly = true)
    public void save(PostCreateDto postCreateDto) {
        Author author = this.authorRepository.findById(postCreateDto.getAuthorId()).orElseThrow(() -> new EntityNotFoundException ("no authorId"));
        this.postRepository.save(postCreateDto.toEntity(author));
    }

    @Transactional(readOnly = true)
    public List<PostListDto> findAll() {
        return this.postRepository.findAll().stream().map(a -> PostListDto.fromEntity(a)).toList();
    }

    @Transactional(readOnly = true)
    public PostDetailDto findById(Long id) {
        Post post = this.postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("not found postId"));
        return PostDetailDto.fromEntity(post);
    }

}
