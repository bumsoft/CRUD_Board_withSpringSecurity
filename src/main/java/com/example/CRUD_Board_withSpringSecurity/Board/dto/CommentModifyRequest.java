package com.example.CRUD_Board_withSpringSecurity.Board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentModifyRequest {
    private Long id;
    private String content;
}
