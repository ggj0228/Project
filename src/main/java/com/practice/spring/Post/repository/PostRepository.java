package com.practice.spring.Post.repository;

import com.practice.spring.Post.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByDelYn(Pageable pageable, String delYn);
    // auhtor가 쓴 글을 리스트로 표시
    Page<Post> findByAuthorIdAndDelYn(Long authorId, Pageable pageable, String delYn);
}
