package com.example.CRUD_Board_withSpringSecurity.Board.dto;

import com.example.CRUD_Board_withSpringSecurity.Board.domain.entity.Comment;
import com.example.CRUD_Board_withSpringSecurity.Board.domain.entity.Post;

import java.util.ArrayList;
import java.util.List;

public class BoardDtoConverter {

//    public static Post toEntity(PostRequest postRequest)
//    {
//        return null;
//    }
//
//    public static Comment toEntity(CommentRequest commentRequest)
//    {
//        return null;
//    }

    public static PostResponse toResponse(Post post)
    {
        PostResponse postResponse = new PostResponse();
        postResponse.setId(post.getId());
        postResponse.setTitle(post.getTitle());
        postResponse.setContent(post.getContent());
        postResponse.setCreatedDate(post.getCreatedDate());
        postResponse.setModifiedDate(post.getModifiedDate());

        List<Comment> comments = post.getComments();
        List<CommentResponse> commentResponses = new ArrayList<>();
        for (Comment comment : comments)
        {
            commentResponses.add(toResponse(comment));
        }
        postResponse.setComments(commentResponses);

        postResponse.setWriter(post.getWriter().getUserId());

        return postResponse;
    }

    public static CommentResponse toResponse(Comment comment)
    {
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(comment.getId());
        commentResponse.setContent(comment.getContent());
        commentResponse.setCreateDate(comment.getCreateDate());
        commentResponse.setModifiedDate(comment.getModifiedDate());
        commentResponse.setWriter(comment.getWriter().getUserId());

        return commentResponse;
    }
}
