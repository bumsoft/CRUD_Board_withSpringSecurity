package com.example.CRUD_Board_withSpringSecurity.Board.service;

import com.example.CRUD_Board_withSpringSecurity.Board.domain.entity.Comment;
import com.example.CRUD_Board_withSpringSecurity.Board.dto.CommentModifyRequest;
import com.example.CRUD_Board_withSpringSecurity.Board.dto.CommentRequest;
import com.example.CRUD_Board_withSpringSecurity.Board.repository.CommentRepository;
import com.example.CRUD_Board_withSpringSecurity.Board.repository.PostRepository;
import com.example.CRUD_Board_withSpringSecurity.Common.exception.DataNotFoundException;
import com.example.CRUD_Board_withSpringSecurity.Member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberService memberService;

    public void save(CommentRequest commentRequest) throws DataNotFoundException
    {
        Comment comment = new Comment();
        comment.setContent(commentRequest.getContent());
        comment.setPost(postRepository.findById(commentRequest.getPostId()).get());
        comment.setWriter(memberService.getMember(commentRequest.getWriter()));

        commentRepository.save(comment);
    }

    public void modify(CommentModifyRequest commentModifyRequest)
    {
        Comment comment = commentRepository.findById(commentModifyRequest.getId()).get();
        comment.setContent(commentModifyRequest.getContent());
        commentRepository.save(comment);
    }

    public void delete(Long commentId)
    {
        Comment comment = commentRepository.findById(commentId).get();
        commentRepository.delete(comment);
    }
}
