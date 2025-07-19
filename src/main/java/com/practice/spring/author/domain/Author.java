package com.practice.spring.author.domain;

import com.practice.spring.Common.BaseTimeEntity;
import com.practice.spring.Post.domain.Post;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
public class Author extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role = Role.USER;

   @OneToMany(mappedBy = "author" , fetch = FetchType.LAZY)
   @Builder.Default
   private List<Post> postList = new ArrayList<>();

    public void updatePwd(String new_password){
        this.password = new_password;
    }
}
