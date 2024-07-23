package com.example.CRUD_Board_withSpringSecurity.Board.dto;

import com.example.CRUD_Board_withSpringSecurity.Board.domain.entity.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.List;

@Setter
@Getter
public class PostResponse {

    private Long id;

    private String writer;

    private String title;

    private String content;

    private String createdDate;

    private String modifiedDate;

    private List<CommentResponse> comments;
}
