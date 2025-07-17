package com.practice.spring.Service;


import com.practice.spring.Dto.AuthorCreateDto;
import com.practice.spring.Dto.AuthorDetailDto;
import com.practice.spring.Dto.AuthorListDto;
import com.practice.spring.Dto.AuthorUpdatePwDto;
import com.practice.spring.Repository.AuthorRepository;
import com.practice.spring.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return this.authorRepository.findAll().stream().map(a -> a.formListEntity()).toList();
    }

    @Transactional(readOnly = true)
    public AuthorDetailDto findById(Long id) {
        Author author =  this.authorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("id not found"));
        return AuthorDetailDto.fromDetailDto(author);
    }


    public void updatePw(AuthorUpdatePwDto authorUpdatePwDto){
        Author author = this.authorRepository.findByEmail(authorUpdatePwDto.getEmail()).orElseThrow(() -> new NoSuchElementException("email isnt correct"));
        author.updatePwd(authorUpdatePwDto.getPassword());
    }

    public void delete(Long id) {
        Author author = this.authorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("id incorrect"));
        this.authorRepository.delete(author);
    }
}
