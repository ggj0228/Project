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
        Author author = this.authorRepository.findByEmail(authorUpdatePwDto.getEmail()).orElseThrow(() -> new EntityNotFoundException("email isn't correct"));
        String storedPassword = author.getPassword();
        String userKnowCurrentPassword = authorUpdatePwDto.getPassword();
        String userWantPassword = authorUpdatePwDto.getNewPassword();
        // 가입 당시 입력했던 비밀번호랑 사용자가 입력한 비밀번호가 같은지 확인, 다르면 예외
        if(!storedPassword.equals(userKnowCurrentPassword)){
            throw new IllegalArgumentException("check your password you sets");
        }
        // 새로운 비밀번호란 가입당시 비밀번호가 같은지 확인, 같으면 예외
        if(userKnowCurrentPassword.equals(userWantPassword)) {
            throw new IllegalArgumentException("plz input  differnt password you sets in the past.");
        }
        author.updatePwd(authorUpdatePwDto.getNewPassword());
    }

    public void delete(Long id) {
        Author author = this.authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("id incorrect"));
        this.authorRepository.delete(author);
    }
}
