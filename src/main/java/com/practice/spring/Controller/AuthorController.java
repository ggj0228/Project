package com.practice.spring.Controller;


import com.practice.spring.Dto.*;
import com.practice.spring.Service.AuthorService;
import com.practice.spring.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping("/sign")
    public ResponseEntity<String> save(@RequestBody AuthorCreateDto authorCreateDto) {
        this.authorService.save(authorCreateDto);
        return new ResponseEntity<>("sign up complete!", HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<AuthorListDto> findAll() {
        return this.authorService.findAll();
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return new ResponseEntity<>(new CommonDto(this.authorService.findById(id), HttpStatus.OK.value(), "id found"), HttpStatus.OK);
    }

    @PatchMapping("/updatepw")
    public ResponseEntity<String> updatePw(@RequestBody AuthorUpdatePwDto authorUpdatePwDto) {
        this.authorService.updatePw(authorUpdatePwDto);
        return new ResponseEntity<>("password is changed", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        this.authorService.delete(id);
        return new ResponseEntity<>("acconut id deleted", HttpStatus.OK);
    }
}
