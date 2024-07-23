package com.example.CRUD_Board_withSpringSecurity.Board.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {

    private String writer;

    @NotEmpty(message = "제목을 입력하세요.")
    @Size(min = 2, max = 30, message = "제목길이는 2~30이어야합니다.")
    private String title;

    @NotEmpty(message = "내용을 입력하세요.")
    private String content;

}
