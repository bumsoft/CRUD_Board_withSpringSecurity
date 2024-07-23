package com.example.CRUD_Board_withSpringSecurity.Board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponse {

    private Long id;

    private String writer;

    private String content;

    private String createDate;

    private String modifiedDate;
}
