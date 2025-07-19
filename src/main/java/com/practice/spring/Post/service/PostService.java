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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<PostListDto> findAll(Pageable pageable) {
        Page<Post> postList = this.postRepository.findByDelYn(pageable, "N");
        return postList.map(p -> PostListDto.fromEntity(p));
    }

    @Transactional(readOnly = true)
    public PostDetailDto findById(Long id) {
        Post post = this.postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("not found postId"));
        return PostDetailDto.fromEntity(post);
    }

}
