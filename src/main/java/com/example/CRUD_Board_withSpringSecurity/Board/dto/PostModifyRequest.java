package com.example.CRUD_Board_withSpringSecurity.Board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostModifyRequest {
    private Long id;
    private String title;
    private String content;

}
