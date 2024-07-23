package com.example.CRUD_Board_withSpringSecurity.Board.dto;

import com.example.CRUD_Board_withSpringSecurity.Member.domain.entity.Member;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {

    private Long postId;

    private String writer;

    @NotEmpty(message = "내용을 입력하세요.")
    private String content;
}
