package com.practice.spring.author.Service;


import com.practice.spring.author.Dto.AuthorCreateDto;
import com.practice.spring.author.Dto.AuthorDetailDto;
import com.practice.spring.author.Dto.AuthorListDto;
import com.practice.spring.author.Dto.AuthorUpdatePwDto;
import com.practice.spring.author.Repository.AuthorRepository;
import com.practice.spring.author.domain.Author;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Transactional(readOnly = true)
    public void save(AuthorCreateDto authorCreateDto) {
        if(this.authorRepository.findByEmail(authorCreateDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("email is exist");
        }
         this.authorRepository.save(authorCreateDto.toEntity());
    }


    @Transactional(readOnly = true)
    public List<AuthorListDto> findAll() {
        return this.authorRepository.findAll().stream().map(a -> AuthorListDto.fromListEntity(a)).toList();
    }

    @Transactional(readOnly = true)
    public AuthorDetailDto findById(Long id) {
        Author author =  this.authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("id not found"));
        return AuthorDetailDto.fromDetailDto(author);
    }


    public void updatePw(AuthorUpdatePwDto authorUpdatePwDto){
        Author author = this.authorRepository.findByEmail(authorUpdatePwDto.getEmail()).orElseThrow(() -> new EntityNotFoundException("email isnt correct"));
        author.updatePwd(authorUpdatePwDto.getPassword());
    }

    public void delete(Long id) {
        Author author = this.authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("id incorrect"));
        this.authorRepository.delete(author);
    }
}
