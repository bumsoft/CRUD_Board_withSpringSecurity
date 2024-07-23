package com.example.CRUD_Board_withSpringSecurity.Board.domain.entity;

import com.example.CRUD_Board_withSpringSecurity.Member.domain.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @CreatedDate
    @Column(name="created_date")
    private String createDate;

    @LastModifiedDate
    @Column(name="modified_date")
    private String modifiedDate;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    @ManyToOne
    private Member writer;
}
